import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Calculable} from '../../../models/Calculable';

export interface DialogData {
  calculable: Calculable;
}

@Component({
  selector: 'app-calculable-modal',
  templateUrl: './calculable-modal.component.html',
  styleUrls: ['./calculable-modal.component.scss']
})
export class CalculableModalComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {}

}
