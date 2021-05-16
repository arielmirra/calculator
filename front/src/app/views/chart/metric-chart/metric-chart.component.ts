import {Component, Input, OnInit} from '@angular/core';
import {Metric} from '../../../models/Metric';
import {ChartDataSets} from 'chart.js';
import {MeasurementService} from '../../../services/measurement.service';
import {Measurement} from '../../../models/Measurement';
import {DatePipe} from '@angular/common';

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
    public measurementService: MeasurementService,
    private datePipe: DatePipe
  ) { }

  ngOnInit(): void {
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
    this.measurementValues = this.measures.map((m) => m.value);
    console.log('MEASUREMENT VALUES');
    console.log(this.measurementValues);
  }

  private setLabels(): void {
    this.measurementDates = this.measures.map((m) => this.datePipe.transform(m.date, 'dd/MM/yy H:mm'));
    console.log('MEASUREMENT DATES');
    console.log(this.measurementDates);
  }
}
