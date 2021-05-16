import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
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
export class MetricChartComponent implements OnInit, OnChanges {

  @Input() metric: Metric;
  @Input() lastMeasurement!: Measurement;

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
      this.setMeasures(list);
    } );
  }

  private setMeasures(list: Measurement[]): void {
    this.measures = list;
    this.setValues();
    this.setLabels();
    this.chartDataSet = {
      data: this.measurementValues, label: this.metric.name
    };
  }

  private setValues(): void {
    this.measurementValues = this.measures.map((m) => m.value);
  }

  private setLabels(): void {
    this.measurementDates = this.measures.map((m) => this.datePipe.transform(m.date, 'dd/MM/yy H:mm'));
  }

  public updateMeasures(measurement: Measurement): void {
    this.setMeasures([...this.measures, measurement]);
  }

  ngOnChanges(changes: SimpleChanges): void {
    for (const prop in changes){
      if (prop === 'lastMeasurement') {
        this.lastMeasurement = changes[prop].currentValue;
      }
    }
    if (this.lastMeasurement && this.lastMeasurement.metricId === this.metric.id) {
      this.updateMeasures(this.lastMeasurement);
      console.log(this.lastMeasurement);
      console.log(this.chartDataSet);
    }
  }
}
