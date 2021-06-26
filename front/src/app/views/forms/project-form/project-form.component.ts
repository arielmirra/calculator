import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {SnackbarService} from '../../../services/snackbar.service';
import {FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {ProjectService} from '../../../services/project.service';
import {ProjectForm} from '../../../models/Project';
import {Calculable, CalculableType} from '../../../models/Calculable';
import {CalculableService} from '../../../services/calculable.service';

@Component({
  selector: 'app-project-form',
  templateUrl: './project-form.component.html',
  styleUrls: ['./project-form.component.scss']
})
export class ProjectFormComponent implements OnInit {
  form: FormGroup;
  metrics: Calculable[];
  selectedMetrics: number[] = [];

  constructor(
    private router: Router,
    private snackbarService: SnackbarService,
    private metricService: CalculableService,
    private projectService: ProjectService
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
    this.metricService.fetchAll().subscribe(list =>
      this.metrics = list.filter(i => i.calculableType === CalculableType.METRIC));
  }

  hasError(controlName: string, errorName: string): boolean {
    return this.form.controls[controlName].hasError(errorName);
  }

  newProject(formDirective: FormGroupDirective): void {
    const form = ProjectForm.empty();
    form.name = this.form.controls.name.value;
    form.description = this.form.controls.description.value;
    form.calculables = this.selectedMetrics;
    console.log(form);
    this.projectService.addProject(form).subscribe(success => {
      this.resetForm(formDirective);
      if (success) {
        this.fetch();
        this.snackbarService.openSnackbar('Proyecto guardado satisfactoriamente', 'Crear Métricas', '/metric/new');
      } else {
        this.snackbarService.openSnackbar('No se ha podido guardar los cambios');
      }
    });
  }

  private resetForm(formDirective: FormGroupDirective): void {
    this.form.reset();
    formDirective.resetForm();
    this.selectedMetrics = [];
  }
}
