import {Component, OnInit} from '@angular/core';
import {Metric} from '../../../models/Metric';
import {SnackbarService} from '../../../services/snackbar.service';
import {Router} from '@angular/router';
import {MetricService} from '../../../services/metric.service';
import {MatDialog} from '@angular/material/dialog';
import {MetricMeasurementModalComponent} from './metric-measurement-modal/metric-measurement-modal.component';

@Component({
  selector: 'app-metric-list',
  templateUrl: './metric-list.component.html',
  styleUrls: ['./metric-list.component.scss']
})
export class MetricListComponent implements OnInit {
  metrics: Metric[];

  constructor(
    private metricService: MetricService,
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

}
