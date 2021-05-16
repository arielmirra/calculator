import {Component, Input, OnInit} from '@angular/core';
import {Metric} from '../../../models/Metric';
import {ChartDataSets} from 'chart.js';
import {MeasurementService} from '../../../services/measurement.service';
import {Measurement} from '../../../models/Measurement';

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

  constructor(
    public measurementService: MeasurementService
  ) { }

  ngOnInit(): void {
    // TODO get Measurements of this.metric
    // this.measures = [
    //   {_id: 1, name: 'first', value: 14, date: new Date('3/3/2021')},
    //   {_id: 2, name: 'second', value: 15, date: new Date('3/4/2021')},
    //   {_id: 3, name: 'third', value: 13, date: new Date('3/5/2021')},
    // ];
    this.fetch();

  }

  private fetch(): void {
    this.measurementService.getMeasurementFromMetric(this.metric.id).subscribe(list => {
      console.log(list);
      this.measures = list;
      this.setValues();
      this.setLabels();
      this.chartDataSet = {
        data: this.measurementValues, label: this.metric.name
      };
    } );
  }

  private setValues(): void {
    this.measurementValues = this.measures.map((m, index) => m.value);
    console.log('MEASUREMENT VALUES');
    console.log(this.measurementValues);
  }

  private setLabels(): void {
    this.measurementDates = this.measures.map((m, index) => m.date.getDate() + '');
    console.log('MEASUREMENT DATES');
    console.log(this.measurementDates);
  }
}
