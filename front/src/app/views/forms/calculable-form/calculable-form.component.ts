import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {SnackbarService} from '../../../services/snackbar.service';
import {CalculableForm} from '../../../models/Calculable';
import {CalculableService} from '../../../services/calculable.service';

@Component({
  selector: 'app-calculable-form',
  templateUrl: './calculable-form.component.html',
  styleUrls: ['./calculable-form.component.scss']
})
export class CalculableFormComponent implements OnInit {

  form: FormGroup;

  constructor(
    private router: Router,
    private snackbarService: SnackbarService,
    private calculableService: CalculableService
  ) {
  }

  ngOnInit(): void {
    const minInputLength = 1;
    const maxInputLength = 25;
    this.form = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(minInputLength), Validators.maxLength(maxInputLength)])
    });
  }

  newVariable(formDirective: FormGroupDirective): void {
    const form = CalculableForm.empty();
    form.name = this.form.controls.name.value;
    console.log(form);
    this.calculableService.addCalculable(form).subscribe(success => {
      this.resetForm(formDirective);
      if (success) {
        this.snackbarService.openSnackbar('Variable guardada satisfactoriamente');
      } else {
        this.snackbarService.openSnackbar('No se ha podido guardar los cambios');
      }
    });
  }

  private resetForm(formDirective: FormGroupDirective): void {
    formDirective.resetForm();
    this.form.reset();
  }

  hasError(controlName: string, errorName: string): boolean {
    return this.form.controls[controlName].hasError(errorName);
  }
}
