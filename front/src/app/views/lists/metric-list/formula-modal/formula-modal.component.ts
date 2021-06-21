import {Component, OnInit} from '@angular/core';
import {Formula} from '../../../../models/Formula';

export interface DialogData {
  formula: Formula;
}

@Component({
  selector: 'app-formula-modal',
  templateUrl: './formula-modal.component.html',
  styleUrls: ['./formula-modal.component.scss']
})
export class FormulaModalComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
