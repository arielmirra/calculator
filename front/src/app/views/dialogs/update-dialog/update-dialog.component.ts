import {Component, Inject, OnInit} from '@angular/core';
import {Calculable} from '../../../models/Calculable';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-update-dialog',
  templateUrl: './update-dialog.component.html',
  styleUrls: ['./update-dialog.component.scss']
})
export class UpdateDialogComponent implements OnInit {

  calculable: Calculable;
  everyCalculable: Calculable[];

  constructor(
    public dialogRef: MatDialogRef<UpdateDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
  ) { }

  ngOnInit(): void {
    this.calculable = this.data.actual;
    console.log(this.calculable);
    this.everyCalculable = this.data.calculables.filter(c => c._id !== this.data.actual._id);
    console.log(this.everyCalculable);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
