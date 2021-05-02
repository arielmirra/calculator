import {Component, Inject, OnInit} from '@angular/core';
import {Calculable} from '../../../models/Calculable';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Metric, MetricForm} from '../../../models/Metric';

@Component({
  selector: 'app-update-metric-dialog',
  templateUrl: './update-metric-dialog.component.html',
  styleUrls: ['./update-metric-dialog.component.scss']
})
export class UpdateMetricDialogComponent implements OnInit {

  metric: Metric;
  metricForm: MetricForm;
  everyCalculable: Calculable[];
  everyMetric: Metric[];

  constructor(
    public dialogRef: MatDialogRef<UpdateMetricDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
  ) { }

  ngOnInit(): void {
    console.log(this.data.actual);
    console.log(this.data.calculables);
    this.metric = this.data.actual;
    this.everyMetric = this.data.metrics.filter(c => c._id !== this.data.actual._id);
    this.everyCalculable = this.data.calculables;
    this.metricForm = MetricForm.empty();
    this.metricForm.id = this.metric._id;
    this.metricForm.name = this.metric.name;
    this.metricForm.description = this.metric.description;
    this.metricForm.calculates = this.metric.calculates.map(c => c._id);
    this.metricForm.metrics = this.metric.metrics.map(c => c._id);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isValid(): boolean {
    return this.metricForm.name !== null && this.metricForm.description !== null;
  }

}
