import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Measurement} from '../../../models/Measurement';
import {ChartDataSets, ChartPoint} from 'chart.js';
import {MeasurementService} from '../../../services/measurement.service';
import {Project} from '../../../models/Project';
import {SnackbarService} from '../../../services/snackbar.service';
import {Formula} from '../../../models/Formula';

@Component({
  selector: 'app-project-chart',
  templateUrl: './project-chart.component.html',
  styleUrls: ['./project-chart.component.scss']
})
export class ProjectChartComponent implements OnInit, OnChanges {

  @Input() project: Project;
  @Input() lastMeasurement!: {value: Measurement, of: Formula};

  public measures: Measurement[][] = [];
  public measurementValues: ChartPoint[][] = [];
  public chartDataSet: ChartDataSets[] = [];

  constructor(
    public measurementService: MeasurementService,
    private snackbarService: SnackbarService,
  ) { }

  ngOnInit(): void {
    this.fetch();
  }

  private fetch(): void {
    this.project.calculables.map((m, index) => {
      this.measurementService.getMeasurementFromMetric(m.id).subscribe(list => {
        this.setMeasures(list, index, m.name);
      });
    });
    console.log(this.chartDataSet);
  }

  private setMeasures(list: Measurement[], index: number, metricName: string): void {
    this.measures[index] = list;
    this.setValues(index);
    this.chartDataSet[index] = {
      data: this.measurementValues[index], label: metricName
    };
  }

  private setValues(index: number): void {
    this.measurementValues[index] = this.measures[index].map((m) => {
      return {y: m.value, x: m.date};
    });
  }

  public updateMeasures(measurement: Measurement, metric: string): void {
    let index = 0;
    this.chartDataSet.map((c, i) => {
      c.label === metric ? index = i : index = -1;
    });
    if (index === -1) {
      this.snackbarService.openSnackbar('wrong metric for project');
    }
    this.setMeasures([...this.measures[index], measurement], index, metric);
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.fetch();
    for (const prop in changes){
      if (prop === 'lastMeasurement') {
        this.lastMeasurement = changes[prop].currentValue;
      }
    }
    if (this.lastMeasurement && this.project.calculables.filter(m => m.id === this.lastMeasurement.of.id)) {
      this.updateMeasures(this.lastMeasurement.value, this.lastMeasurement.of.calcTree.name);
      console.log(this.lastMeasurement);
      console.log(this.chartDataSet);
    }
  }

}
