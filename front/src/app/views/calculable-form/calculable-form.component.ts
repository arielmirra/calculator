import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {SnackbarService} from '../../services/snackbar.service';
import {Calculable, CalculableForm, Operator} from '../../models/Calculable';
import {CalculableService} from '../../services/calculable.service';

@Component({
  selector: 'app-calculable-form',
  templateUrl: './calculable-form.component.html',
  styleUrls: ['./calculable-form.component.scss']
})
export class CalculableFormComponent implements OnInit {

  simpleForm: FormGroup;
  form: FormGroup;
  operator: Operator;
  node1: number;
  node2: number;
  calculables: Calculable[];

  constructor(
    private router: Router,
    private snackbarService: SnackbarService,
    private calculableService: CalculableService
  ) {
  }

  ngOnInit(): void {
    const minInputLength = 1;
    const maxInputLength = 25;

    this.simpleForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(minInputLength), Validators.maxLength(maxInputLength)]),
      value: new FormControl(null, [Validators.required])
    });

    this.form = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(minInputLength), Validators.maxLength(maxInputLength)]),
    });

    this.fetch();
  }

  fetch(): void {
    this.calculableService.fetchAll().subscribe(list => this.calculables = list);
  }

  newCalc(formDirective: FormGroupDirective): void {
    const form = CalculableForm.empty();
    form.name = this.form.controls.name.value;
    form.left = this.node1;
    form.right = this.node2;
    form.operator = this.operator;
    console.log(form);
    this.calculableService.addCalculable(form).subscribe(success => {
      this.resetForm(formDirective);
      if (success) {
        this.fetch();
        this.snackbarService.openSnackbar('Cálculo guardado satisfactoriamente');
      } else {
        this.snackbarService.openSnackbar('No se ha podido guardar los cambios');
      }
    });
  }

  newSimpleCalc(formDirective: FormGroupDirective): void {
    const form = CalculableForm.empty();
    form.name = this.simpleForm.controls.name.value;
    form.value = this.simpleForm.controls.value.value;
    console.log(form);
    this.calculableService.addCalculable(form).subscribe(success => {
      this.resetForm(formDirective);
      if (success) {
        this.fetch();
        this.snackbarService.openSnackbar('Cálculo guardado satisfactoriamente');
      } else {
        this.snackbarService.openSnackbar('No se ha podido guardar los cambios');
      }
    });
  }

  hasError(controlName: string, errorName: string): boolean {
    return this.form.controls[controlName].hasError(errorName);
  }

  private resetForm(formDirective: FormGroupDirective): void {
    formDirective.resetForm();
    this.simpleForm.reset();
    this.form.reset();
    this.operator = null;
    this.node1 = null;
    this.node2 = null;
  }
}
