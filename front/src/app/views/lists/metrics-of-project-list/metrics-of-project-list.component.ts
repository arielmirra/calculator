import { Component, OnInit } from '@angular/core';
import {Project} from '../../../models/Project';
import {Calculable, CalculableType} from '../../../models/Calculable';
import {Measurement} from '../../../models/Measurement';
import {CalculableService} from '../../../services/calculable.service';
import {FormulaService} from '../../../services/formula.service';
import {SnackbarService} from '../../../services/snackbar.service';
import {MatDialog} from '@angular/material/dialog';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../../../services/project.service';
import {UpdateCalculableDialogComponent} from '../../dialogs/update-calculable-dialog/update-calculable-dialog.component';
import {FormulaModalComponent} from '../metric-list/formula-modal/formula-modal.component';

@Component({
  selector: 'app-metrics-of-project-list',
  templateUrl: './metrics-of-project-list.component.html',
  styleUrls: ['./metrics-of-project-list.component.scss']
})
export class MetricsOfProjectListComponent implements OnInit {

  project: Project;
  calculables: Calculable[];
  lastMeasurement?: Measurement;

  constructor(
    private projectService: ProjectService,
    private calculableService: CalculableService,
    private formulaService: FormulaService,
    private snackbar: SnackbarService,
    public dialog: MatDialog,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.fetch();
  }

  fetch(): void {
    const id: number = +this.route.snapshot.paramMap.get('id');
    this.projectService.getProject(id).subscribe(result => {
      this.project = result;
    });
    this.calculableService.fetchAll().subscribe(r => {
      this.calculables = r;
    });
  }

  seeMetrics(metric: Calculable): void {
    this.router.navigate(['metric/metrics/' + metric.id]);
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
