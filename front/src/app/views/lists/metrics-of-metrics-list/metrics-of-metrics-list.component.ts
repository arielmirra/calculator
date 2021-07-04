import {Component, Input, OnInit} from '@angular/core';
import {Calculable, CalculableType} from '../../../models/Calculable';
import {CalculableService} from '../../../services/calculable.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Measurement} from '../../../models/Measurement';
import {ProjectService} from "../../../services/project.service";
import {FormulaService} from "../../../services/formula.service";
import {SnackbarService} from "../../../services/snackbar.service";
import {MatDialog} from "@angular/material/dialog";
import {FormulaModalComponent} from "../metric-list/formula-modal/formula-modal.component";
import {UpdateCalculableDialogComponent} from "../../dialogs/update-calculable-dialog/update-calculable-dialog.component";

@Component({
  selector: 'app-metrics-of-metrics-list',
  templateUrl: './metrics-of-metrics-list.component.html',
  styleUrls: ['./metrics-of-metrics-list.component.scss']
})
export class MetricsOfMetricsListComponent implements OnInit {

  metric: Calculable;
  calculables: Calculable[];
  lastMeasurement?: Measurement;

  constructor(
    private calculableService: CalculableService,
    private route: ActivatedRoute,
    private formulaService: FormulaService,
    private snackbar: SnackbarService,
    public dialog: MatDialog,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.fetch();
  }

  fetch(): void {
    const id: number = +this.route.snapshot.paramMap.get('id');
    this.calculableService.getCalculable(id).subscribe(result => {
      this.metric = result;
    });
    this.calculableService.fetchAll().subscribe(r => {
      this.calculables = r;
    });
  }

  measure(m: Calculable): void {
    this.calculableService.measure(m.id).subscribe(formula => {
      const dialogRef = this.dialog.open(FormulaModalComponent, {
        width: '550px',
        data: {formula}
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
        this.formulaService.measure(formula.fromId, formula).subscribe(res => {
          if (res){
            this.lastMeasurement = res;
            console.log(this.lastMeasurement);
          } else {
            console.log('ERROR');
          }
        });
      });
    });
  }

  openDialog(metric: Calculable): void {
    const dialogRef = this.dialog.open(UpdateCalculableDialogComponent, {
      width: '400px',
      data: {actual: metric, calculables: this.calculables}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.calculableService.updateCalculable(result, result.id).subscribe(success => {
        if (success) {
          this.snackbar.openSnackbar('Métrica guardada satisfactoriamente');
          this.fetch();
        } else {
          this.snackbar.openSnackbar('No se ha podido guardar los cambios');
        }
      });
    });
  }
}
