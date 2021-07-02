import {Component, Input, OnInit} from '@angular/core';
import {Calculable, CalculableType} from '../../../models/Calculable';
import {CalculableService} from '../../../services/calculable.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-metrics-of-metrics-list',
  templateUrl: './metrics-of-metrics-list.component.html',
  styleUrls: ['./metrics-of-metrics-list.component.scss']
})
export class MetricsOfMetricsListComponent implements OnInit {

  metric: Calculable;

  constructor(
    private calculableService: CalculableService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    const id: number = +this.route.snapshot.paramMap.get('id');
    this.calculableService.getCalculable(id).subscribe(result => {
      this.metric = result;
    });
  }
}
