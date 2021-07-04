import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ChartDataSets, ChartPoint} from 'chart.js';
import {MeasurementService} from '../../../services/measurement.service';
import {Measurement} from '../../../models/Measurement';
import {DatePipe} from '@angular/common';
import {Calculable} from '../../../models/Calculable';

@Component({
  selector: 'app-metric-chart',
  templateUrl: './metric-chart.component.html',
  styleUrls: ['./metric-chart.component.scss']
})
export class MetricChartComponent implements OnInit, OnChanges {

  @Input() metric: Calculable;
  @Input() lastMeasurement!: Measurement;

  public measures: Measurement[];
  public measurementValues: ChartPoint[];
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
    this.measurementValues = this.measures.map((m) => {
      return {y: m.value, x: m.date};
    });
  }

  private setLabels(): void {
    this.measurementDates = this.measures.map((m) => this.datePipe.transform(m.date, 'dd/MM/yy H:mm'));
  }

  public updateMeasures(measurement: Measurement): void {
    this.setMeasures([...this.measures, measurement]);
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.fetch();
    for (const prop in changes){
      if (prop === 'lastMeasurement') {
        this.lastMeasurement = changes[prop].currentValue;
      }
    }
    if (this.lastMeasurement && this.lastMeasurement.from.fromId === this.metric.id) {
      this.updateMeasures(this.lastMeasurement);
    }
  }
}
