import {Component, OnInit} from '@angular/core';
import {SnackbarService} from '../../../services/snackbar.service';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {MetricMeasurementModalComponent} from './metric-measurement-modal/metric-measurement-modal.component';
import {Calculable} from '../../../models/Calculable';
import {UpdateMetricDialogComponent} from '../../dialogs/update-metric-dialog/update-metric-dialog.component';
import {CalculableService} from '../../../services/calculable.service';
import {Measurement} from '../../../models/Measurement';

@Component({
  selector: 'app-metric-list',
  templateUrl: './metric-list.component.html',
  styleUrls: ['./metric-list.component.scss']
})
export class MetricListComponent implements OnInit {
  metrics: Calculable[];
  calculables: Calculable[];
  lastMeasurement?: Measurement;

  constructor(
    private calculableService: CalculableService,
    private snackbar: SnackbarService,
    public dialog: MatDialog,
    private router: Router) {
  }

  ngOnInit(): void {
    this.fetch();
  }

  fetch(): void {
    this.metricService.fetchAll().subscribe(list => {
      console.log(list);
      this.metrics = list;
    });
    this.calculableService.fetchAll().subscribe(r => this.calculables = r);
  }

  measure(m: Calculable): void {
    this.metricService.measure(m.id).subscribe(result => {
      this.dialog.open(MetricMeasurementModalComponent, {
        width: '550px',
        data: {measure: result}
      });
      this.lastMeasurement = result;
      console.log(this.lastMeasurement);
    });
  }

  createMetric(): void {
    this.router.navigate(['metric/new']);
  }

  openDialog(metric: Calculable): void {
    const dialogRef = this.dialog.open(UpdateMetricDialogComponent, {
      width: '400px',
      data: {actual: metric, metrics: this.metrics, calculables: this.calculables}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.metricService.updateMetric(result).subscribe(success => {
        if (success) {
          this.snackbar.openSnackbar('Cálculo guardado satisfactoriamente');
          this.fetch();
        } else {
          this.snackbar.openSnackbar('No se ha podido guardar los cambios');
        }
      });
    });
  }

}
