import {Component, Inject, OnInit} from '@angular/core';
import {Formula} from '../../../../models/Formula';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-formula-modal',
  templateUrl: './formula-modal.component.html',
  styleUrls: ['./formula-modal.component.scss']
})
export class FormulaModalComponent implements OnInit {

  formula: Formula;
  variables: [string, number][];

  constructor(
    public dialogRef: MatDialogRef<FormulaModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
  ) {}

  ngOnInit(): void {
    this.formula = this.data.formula;
    const values = Array.from(this.formula.variables.values());
    this.variables = Array.from(this.formula.variables.keys())
      .map((k, index) => [k, values[index]]);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  getFinalFormula(): Formula {
    this.formula.variables = new Map<string, number>(this.variables);
    return this.formula;
  }

}
