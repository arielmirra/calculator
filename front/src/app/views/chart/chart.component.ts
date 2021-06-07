import {Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {ChartDataSets, ChartOptions, ChartType} from 'chart.js';
import {BaseChartDirective, Label, Color} from 'ng2-charts';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit, OnChanges {
  @Input() dataSet?: ChartDataSets[];
  @Input() labels?: Label[];
  @Input() options?: (ChartOptions & { annotation: any });

  public lineChartData: ChartDataSets[] = [
    { data: [65, 59, 80, 81, 56, 55, -20, 70, 80, 60], label: 'Series A' },
    { data: [28, 48, 40, 19, 0, 86, 27, 90], label: 'Series B' },
    { data: [80, 90, 70, 90, 10, 20, -1], label: 'Series C'}
  ];
  public lineChartLabels: Label[] = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September'];
  public lineChartOptions: (ChartOptions & { annotation: any }) = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      xAxes: [{
        type: 'time'
      }],
      yAxes: [
        {
          id: 'y',
          type: 'linear',
          position: 'left',
        }
      ]
    },
    annotation: {
      annotations: [
        {
          type: 'line',
          mode: 'horizontal',
          scaleID: 'y',
          value: 3.0,
          borderWidth: 2,
          borderColor: 'rgb(75, 0, 0)',
          label: {
            enabled: true,
            content: 'threshold'
          }
        },
      ],
    },
  };
  public lineChartColors: Color[] = [
    { // blue
      backgroundColor: 'rgba(15,93,219,0)',
      borderColor: 'rgb(66,114,193)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // violet
      backgroundColor: 'rgba(88,9,198,0)',
      borderColor: 'rgb(116,73,191)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(77,83,96,1)'
    },
    { // green
      backgroundColor: `rgba(0, 255, 0, 0)`,
      borderColor: 'rgb(66,193,83)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];
  public lineChartLegend = true;
  public lineChartType: ChartType = 'line';

  @ViewChild(BaseChartDirective, { static: true }) chart: BaseChartDirective;
  constructor() { }

  ngOnInit(): void {
    if (this.options) {this.lineChartOptions = this.options; }
    if (this.dataSet) {this.lineChartData = this.dataSet; }
    if (this.labels) {this.lineChartLabels = this.labels; }
  }

  ngOnChanges(changes: SimpleChanges): void {
    for (const prop in changes){
      if (prop === 'dataSet') {
        this.lineChartData = changes[prop].currentValue;
      }
       else if (prop === 'labels') {
        this.lineChartLabels = changes[prop].currentValue;
      }
    }
  }
}
