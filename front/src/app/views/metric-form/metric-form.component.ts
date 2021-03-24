import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Calculable} from '../../models/Calculable';
import {Router} from '@angular/router';
import {SnackbarService} from '../../services/snackbar.service';
import {CalculableService} from '../../services/calculable.service';
import {MetricService} from '../../services/metric.service';
import {Metric, MetricForm} from '../../models/Metric';

@Component({
  selector: 'app-metric-form',
  templateUrl: './metric-form.component.html',
  styleUrls: ['./metric-form.component.scss']
})
export class MetricFormComponent implements OnInit {
  form: FormGroup;
  metrics: Metric[];
  calculables: Calculable[];
  selectedMetrics: number[] = [];
  selectedCalculables: number[] = [];

  constructor(
    private router: Router,
    private snackbarService: SnackbarService,
    private calculableService: CalculableService,
    private metricService: MetricService
  ) { }

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
    this.calculableService.fetchAll().subscribe(list => this.calculables = list);
    this.metricService.fetchAll().subscribe(list => this.metrics = list);
  }

  hasError(controlName: string, errorName: string): boolean {
    return this.form.controls[controlName].hasError(errorName);
  }

  newMetric(): void {
    const form = MetricForm.empty();
    form.name = this.form.controls.name.value;
    form.description = this.form.controls.description.value;
    form.metrics = this.selectedMetrics;
    form.calculates = this.selectedCalculables;
    console.log(form);
    this.metricService.addMetric(form).subscribe(success => {
      this.resetForm();
      if (success) {
        this.fetch();
        this.snackbarService.openSnackbar('Cálculo guardado satisfactoriamente');
      } else {
        this.snackbarService.openSnackbar('No se ha podido guardar los cambios');
      }
    });
  }

  private resetForm(): void {
    this.form.reset();
    this.selectedCalculables = [];
    this.selectedMetrics = [];
  }
}
