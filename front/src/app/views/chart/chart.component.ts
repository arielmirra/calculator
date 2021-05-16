import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ChartDataSets, ChartOptions, ChartType} from 'chart.js';
import {BaseChartDirective, Label, Color} from 'ng2-charts';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit {
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
    responsive: false,
    maintainAspectRatio: false,
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      xAxes: [{}],
      yAxes: [
        {
          id: 'y-axis-0',
          position: 'left',
        }
      ]
    },
    annotation: {
      annotations: [
        {
          type: 'line',
          mode: 'horizontal',
          scaleID: 'y-axis-0',
          value: 10,
          borderWidth: 2,
          label: {
            enabled: true,
            fontColor: 'orange',
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
      borderColor: 'green',
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

  private generateNumber(i: number): number {
    return Math.floor((Math.random() * (i < 2 ? 100 : 1000)) + 1);
  }

  // public pushNew(dot: {lable}): void {
  //   this.lineChartData.forEach((x, i) => {
  //     const num = this.generateNumber(i);
  //     const data: number[] = x.data as number[];
  //     data.push(num);
  //   });
  //   this.lineChartLabels.push(`Label ${this.lineChartLabels.length}`);
  // }

}
