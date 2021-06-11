import {Component, Inject, OnInit} from '@angular/core';
import {Calculable, CalculableForm} from '../../../models/Calculable';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-update-dialog',
  templateUrl: './update-calculable-dialog.component.html',
  styleUrls: ['./update-calculable-dialog.component.scss']
})
export class UpdateCalculableDialogComponent implements OnInit {

  calculable: Calculable;
  calculableForm: CalculableForm;
  everyCalculable: Calculable[];

  constructor(
    public dialogRef: MatDialogRef<UpdateCalculableDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
  ) { }

  ngOnInit(): void {
    this.calculable = this.data.actual;
    this.everyCalculable = this.data.calculables.filter(c => c.id !== this.data.actual.id);
    console.log(this.calculable);
    this.calculableForm = CalculableForm.empty();
    this.calculableForm.id = this.calculable.id;
    this.calculableForm.name = this.calculable.name;
    this.calculableForm.left = this.calculable.left ? this.calculable.left.id : null;
    this.calculableForm.right = this.calculable.right ? this.calculable.right.id : null;
    this.calculableForm.operator = this.calculable.operator;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
