import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Calculable} from '../../../models/Calculable';
import {CalculableService} from '../../../services/calculable.service';
import {SnackbarService} from '../../../services/snackbar.service';

export interface DialogData {
  node1: Calculable;
  node2: Calculable;
  input1: string;
  input2: string;
}

@Component({
  selector: 'app-calculable-modal',
  templateUrl: './calculable-modal.component.html',
  styleUrls: ['./calculable-modal.component.scss']
})
export class CalculableModalComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    public dialogRef: MatDialogRef<CalculableModalComponent>,
    private calcService: CalculableService,
    private snackbarService: SnackbarService
  ) {
  }

  ngOnInit(): void {
  }


  confirmDisabled(): boolean {
    return !(!!this.data.node1 && !!this.data.node2);
  }

  getNode(input: string, left: boolean): void {
    this.calcService.getCalculableByName(input).subscribe(calc => {
      if (calc) {
        if (left) {
          this.data.node1 = calc;
        } else {
          this.data.node2 = calc;
        }
        this.snackbarService.openSnackbar(`Nodo ${input} encontrado y seleccionado`);
      } else {
        this.snackbarService.openSnackbar(`El nodo ${input} no existe`);
      }
    });
  }
}
