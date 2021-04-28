import {Component, OnInit} from '@angular/core';
import {Metric, MetricForm} from '../../../models/Metric';
import {SnackbarService} from '../../../services/snackbar.service';
import {Router} from '@angular/router';
import {MetricService} from '../../../services/metric.service';
import {MatDialog} from '@angular/material/dialog';
import {MetricMeasurementModalComponent} from './metric-measurement-modal/metric-measurement-modal.component';
import {Calculable, CalculableForm} from '../../../models/Calculable';
import {UpdateCalculableDialogComponent} from '../../dialogs/update-calculable-dialog/update-calculable-dialog.component';
import {UpdateMetricDialogComponent} from '../../dialogs/update-metric-dialog/update-metric-dialog.component';
import {CalculableService} from '../../../services/calculable.service';

@Component({
  selector: 'app-metric-list',
  templateUrl: './metric-list.component.html',
  styleUrls: ['./metric-list.component.scss']
})
export class MetricListComponent implements OnInit {
  metrics: Metric[];
  calculables: Calculable[];

  constructor(
    private metricService: MetricService,
    private calculableService: CalculableService,
    private snackbar: SnackbarService,
    public dialog: MatDialog,
    private router: Router) {
  }

  ngOnInit(): void {
    this.fetch();
    this.calculableService.fetchAll().subscribe(r => this.calculables = r);
  }

  fetch(): void {
    this.metricService.fetchAll().subscribe(list => {
      console.log(list);
      this.metrics = list;
    });
  }

  measure(m: Metric): void {
    this.metricService.measure(m._id).subscribe(result => {
      this.dialog.open(MetricMeasurementModalComponent, {
        width: '550px',
        data: {measure: result}
      });
    });
  }

  createMetric(): void {
    this.router.navigate(['metric/new']);
  }

  openDialog(metric: Metric): void {
    const dialogRef = this.dialog.open(UpdateMetricDialogComponent, {
      width: '250px',
      data: {actual: metric, metrics: this.metrics, calculables: this.calculables}
    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log(result);
      // const form = MetricForm.empty();
      // form.name = result.name;
      // form.description = result.description;
      // form.calculates = result.calculates.map(c => c._id);
      // form.metrics = result.metrics.map(c => c._id);
      console.log(result);
      this.metricService.updateMetric(result).subscribe(success => {
        if (success) {
          this.snackbar.openSnackbar('Cálculo guardado satisfactoriamente');
        } else {
          this.snackbar.openSnackbar('No se ha podido guardar los cambios');
        }
      });
    });
  }

}
