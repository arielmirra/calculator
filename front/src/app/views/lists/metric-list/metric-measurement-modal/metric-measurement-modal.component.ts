import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Measurement} from '../../../../models/Metric';

export interface DialogData {
  measure: Measurement;
}

@Component({
  selector: 'app-metric-measurement-modal',
  templateUrl: './metric-measurement-modal.component.html',
  styleUrls: ['./metric-measurement-modal.component.scss']
})
export class MetricMeasurementModalComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {
  }

}
