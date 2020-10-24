import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {SnackbarService} from '../../services/snackbar.service';
import {CalculableForm, Operator} from '../../models/Calculable';
import {CalculableService} from '../../services/calculable.service';

@Component({
  selector: 'app-calculable-form',
  templateUrl: './calculable-form.component.html',
  styleUrls: ['./calculable-form.component.scss']
})
export class CalculableFormComponent implements OnInit {

  form: FormGroup;
  operator: Operator;

  constructor(
    private router: Router,
    private snackbarService: SnackbarService,
    private calculableService: CalculableService
  ) {
  }

  ngOnInit(): void {
    const minInputLength = 3;
    const maxInputLength = 25;

    this.form = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(minInputLength), Validators.maxLength(maxInputLength)]),
      left: new FormControl(null),
      right: new FormControl(null),
      operator: new FormControl(null),
      value: new FormControl(null)
    });
  }

  newCalc(): void {
    const form = CalculableForm.empty();
    form.name = this.form.controls.name.value;
    form.left = this.form.controls.left.value;
    form.right = this.form.controls.right.value;
    form.operator = this.operator;
    form.value = this.form.controls.value.value;
    console.log(form);
    this.calculableService.addCalculable(form).subscribe(success => {
      if (success) {
        this.snackbarService.openSnackbar('Calculable guardado satisfactoriamente');
      } else {
        this.snackbarService.openSnackbar('No se ha podido guardar los cambios');
      }
    });
  }

  hasError(controlName: string, errorName: string): boolean {
    return this.form.controls[controlName].hasError(errorName);
  }
}
