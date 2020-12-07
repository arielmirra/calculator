import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {SnackbarService} from '../../services/snackbar.service';
import {Calculable, CalculableForm, Operator} from '../../models/Calculable';
import {CalculableService} from '../../services/calculable.service';
import {CalculableModalComponent} from './calculable-modal/calculable-modal.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-calculable-form',
  templateUrl: './calculable-form.component.html',
  styleUrls: ['./calculable-form.component.scss']
})
export class CalculableFormComponent implements OnInit {

  simpleForm: FormGroup;
  form: FormGroup;
  operator: Operator;
  node1: Calculable;
  node2: Calculable;

  constructor(
    private router: Router,
    private snackbarService: SnackbarService,
    private calculableService: CalculableService,
    public dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    const minInputLength = 3;
    const maxInputLength = 25;

    this.simpleForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(minInputLength), Validators.maxLength(maxInputLength)]),
      value: new FormControl(0)
    });

    this.form = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(minInputLength), Validators.maxLength(maxInputLength)]),
      operator: new FormControl(null),
    });
  }

  newCalc(): void {
    const form = CalculableForm.empty();
    form.name = this.form.controls.name.value;
    form.left = this.node1.id;
    form.right = this.node2.id;
    form.operator = this.operator;
    console.log(form);
    this.calculableService.addCalculable(form).subscribe(success => {
      this.resetForm();
      if (success) {
        this.snackbarService.openSnackbar('Cálculo guardado satisfactoriamente');
      } else {
        this.snackbarService.openSnackbar('No se ha podido guardar los cambios');
      }
    });
  }

  newSimpleCalc(): void {
    const form = CalculableForm.empty();
    form.name = this.simpleForm.controls.name.value;
    form.value = this.form.controls.value.value;
    console.log(form);
    this.calculableService.addCalculable(form).subscribe(success => {
      this.resetForm();
      if (success) {
        this.snackbarService.openSnackbar('Cálculo guardado satisfactoriamente');
      } else {
        this.snackbarService.openSnackbar('No se ha podido guardar los cambios');
      }
    });
  }

  hasError(controlName: string, errorName: string): boolean {
    return this.form.controls[controlName].hasError(errorName);
  }

  selectNodes(): void {
    const dialogRef = this.dialog.open(CalculableModalComponent, {
      width: '500px',
      data: {
        node1: null,
        node2: null,
        input1: '',
        input2: ''
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (result !== false) {
        this.node1 = result.node1;
        this.node2 = result.node2;
      }
    });
  }

  private resetForm(): void {
    this.operator = null;
    this.node1 = null;
    this.node2 = null;
  }
}
