import {Component, Input, OnInit} from '@angular/core';
import {Measurement, Metric} from '../../../models/Metric';
import {ChartDataSets} from 'chart.js';

@Component({
  selector: 'app-metric-chart',
  templateUrl: './metric-chart.component.html',
  styleUrls: ['./metric-chart.component.scss']
})
export class MetricChartComponent implements OnInit {

  @Input() metric: Metric;

  public measures: Measurement[];
  public measurementValues: number[];
  public measurementDates: string[];
  public chartDataSet: ChartDataSets;

  constructor() { }

  ngOnInit(): void {
    // TODO get Measurements of this.metric
    this.measures = [
      {_id: 1, name: 'first', value: 14, date: new Date('3/3/2021')},
      {_id: 2, name: 'second', value: 15, date: new Date('3/4/2021')},
      {_id: 3, name: 'third', value: 13, date: new Date('3/5/2021')},
    ];
    this.setValues();
    this.setLabels();
    this.chartDataSet = {
      data: this.measurementValues, label: 'metric name' // TODO change to this.metric.name
    };
  }

  private setValues(): void {
    this.measurementValues = this.measures.map((m, index) => m.value);
  }

  private setLabels(): void {
    this.measurementDates = this.measures.map((m, index) => m.date.toDateString());
  }
}
