import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {Calculable, CalculableForm, CalculableType, Operator} from '../../../models/Calculable';
import {Router} from '@angular/router';
import {SnackbarService} from '../../../services/snackbar.service';
import {CalculableService} from '../../../services/calculable.service';

@Component({
  selector: 'app-metric-form',
  templateUrl: './metric-form.component.html',
  styleUrls: ['./metric-form.component.scss']
})
export class MetricFormComponent implements OnInit {
  form: FormGroup;
  operator: Operator;
  left: number;
  right: number;
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
    this.form = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(minInputLength), Validators.maxLength(maxInputLength)]),
      description: new FormControl('', [Validators.required, Validators.minLength(minInputLength)]),
    });
    this.fetch();
  }

  fetch(): void {
    this.calculableService.fetchAll().subscribe(list =>
      this.calculables = list.filter(i => i.calculableType === CalculableType.METRIC || i.calculableType === CalculableType.VARIABLE));
  }

  hasError(controlName: string, errorName: string): boolean {
    return this.form.controls[controlName].hasError(errorName);
  }

  newMetric(formDirective: FormGroupDirective): void {
    const form = CalculableForm.empty();
    form.name = this.form.controls.name.value;
    form.description = this.form.controls.description.value;
    form.left = this.left;
    form.right = this.right;
    form.operator = this.operator;
    console.log(form);
    this.calculableService.addCalculable(form).subscribe(success => {
      this.resetForm(formDirective);
      if (success) {
        this.fetch();
        this.snackbarService.openSnackbar('Métrica guardada satisfactoriamente', 'Crear Variables', '/variable/new/');
      } else {
        this.snackbarService.openSnackbar('No se ha podido guardar los cambios');
      }
    });
  }

  private resetForm(formDirective: FormGroupDirective): void {
    formDirective.resetForm();
    this.form.reset();
    this.operator = null;
    this.left = null;
    this.right = null;
  }

  isValid(form: FormGroup): boolean {
    return form.valid && !!this.operator;
  }
}
