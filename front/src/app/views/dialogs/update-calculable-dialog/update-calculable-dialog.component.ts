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
    this.everyCalculable = this.data.calculables.filter(c => c._id !== this.data.actual._id);
    console.log(this.calculable);
    this.calculableForm = CalculableForm.empty();
    this.calculableForm.id = this.calculable._id;
    this.calculableForm.name = this.calculable.name;
    this.calculableForm.value = this.calculable.value;
    this.calculableForm.left = this.calculable.left ? this.calculable.left._id : null;
    this.calculableForm.right = this.calculable.right ? this.calculable.right._id : null;
    this.calculableForm.operator = this.calculable.operator;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
