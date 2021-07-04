import {Component, OnInit} from '@angular/core';
import {SnackbarService} from '../../../services/snackbar.service';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {Calculable, CalculableType, Operator} from '../../../models/Calculable';
import {CalculableService} from '../../../services/calculable.service';
import {Measurement} from '../../../models/Measurement';
import {FormulaModalComponent} from './formula-modal/formula-modal.component';
import {UpdateCalculableDialogComponent} from '../../dialogs/update-calculable-dialog/update-calculable-dialog.component';
import {FormulaService} from '../../../services/formula.service';

@Component({
  selector: 'app-metric-list',
  templateUrl: './metric-list.component.html',
  styleUrls: ['./metric-list.component.scss']
})
export class MetricListComponent implements OnInit {
  metrics: Calculable[];
  calculables: Calculable[];
  lastMeasurement?: Measurement;
  Operator = Operator;

  constructor(
    private calculableService: CalculableService,
    private formulaService: FormulaService,
    private snackbar: SnackbarService,
    public dialog: MatDialog,
    private router: Router) {
  }

  ngOnInit(): void {
    this.fetch();
  }

  fetch(): void {
    this.calculableService.fetchAll().subscribe(r => {
      this.calculables = r;
      this.metrics = r.filter(c => c.calculableType === CalculableType.METRIC);
    });
  }

  measure(m: Calculable): void {
    this.calculableService.measure(m.id).subscribe(formula => {
      const dialogRef = this.dialog.open(FormulaModalComponent, {
        width: '550px',
        data: {formula}
      });

      dialogRef.afterClosed().subscribe(result => {
        if (result !== undefined) {
          this.formulaService.measure(formula.fromId, formula).subscribe(success => {
            if (success){
              this.lastMeasurement = success;
              console.log(this.lastMeasurement);
            } else {
              console.log('ERROR');
            }
          });
        } else {
          this.formulaService.deleteFormula(formula.id).subscribe();
        }
      });
    });
  }

  createMetric(): void {
    this.router.navigate(['metric/new']);
  }

  seeMetrics(metric: Calculable): void {
    this.router.navigate(['metric/metrics/' + metric.id]);
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
