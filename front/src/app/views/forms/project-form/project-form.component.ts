import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {SnackbarService} from '../../../services/snackbar.service';
import {FormControl} from '@angular/forms';
import {ProjectService} from '../../../services/project.service';
import {Project, ProjectForm} from '../../../models/Project';
import {Calculable, CalculableForm, CalculableType} from '../../../models/Calculable';
import {CalculableService} from '../../../services/calculable.service';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {Observable} from 'rxjs';
import {MatAutocomplete, MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {map, startWith} from 'rxjs/operators';
import {MatChipInputEvent} from '@angular/material/chips';

@Component({
  selector: 'app-project-form',
  templateUrl: './project-form.component.html',
  styleUrls: ['./project-form.component.scss']
})
export class ProjectFormComponent implements OnInit {
  project: Project;
  projectForm: ProjectForm;
  everyMetric: Calculable[];
  selectedMetrics: Calculable[];

  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  metricCtrl = new FormControl();
  filteredMetrics: Observable<Calculable[]>;

  @ViewChild('metricsInput') metricsInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;

  constructor(
    private router: Router,
    private snackbarService: SnackbarService,
    private metricService: CalculableService,
    private projectService: ProjectService
  ) {
  }

  ngOnInit(): void {
    this.project = Project.empty();
    this.fetch();
    this.projectForm = ProjectForm.empty();
    this.projectForm.id = this.project.id;
    this.projectForm.name = this.project.name;
    this.projectForm.description = this.project.description;
    this.projectForm.calculables = this.project.calculables.map(c => c.id);
  }

  fetch(): void {
    this.metricService.fetchAll().subscribe(list => {
      this.everyMetric = list.filter(i => i.calculableType === CalculableType.METRIC);
      this.selectedMetrics = this.everyMetric.filter(m => this.projectForm.calculables.indexOf(m.id) > -1);
      this.filteredMetrics = this.metricCtrl.valueChanges.pipe(
        startWith(null),
        map((m: string | null) => m ? this._filter(m) : this.left()));
    });
  }

  isValid(): boolean {
    return this.projectForm.name !== '' && this.projectForm.description !== '';
  }

  forceChange(value: string): void{
    this.metricCtrl.setValue(value);
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      const val = this.everyMetric.filter(m => m.name + '' === value.trim())[0];
      if (val){
        this.selectedMetrics.push(val);
      }
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
    this.projectForm.calculables = this.selectedMetrics.map(m => m.id);
    this.metricCtrl.setValue(null);
  }

  remove(metric: number): void {
    const index = this.selectedMetrics.indexOf(this.everyMetric.filter(m => m.id === metric)[0]);

    if (index >= 0) {
      this.selectedMetrics.splice(index, 1);
    }
    this.projectForm.calculables = this.selectedMetrics.map(m1 => m1.id);
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.selectedMetrics.push(this.everyMetric.filter(m => m.name === event.option.value)[0]);
    this.metricsInput.nativeElement.value = '';
    this.metricCtrl.setValue(null);
    this.projectForm.calculables = this.selectedMetrics.map(m1 => m1.id);
  }

  private _filter(value: string): Calculable[] {
    const filterValue = value.toLowerCase();
    return this.left().filter(m => m.name.toLowerCase().indexOf(filterValue) === 0);
  }

  private left(): Calculable[] {
    return this.everyMetric.filter(m => this.selectedMetrics.indexOf(m) < 0);
  }

  newProject(): void {
    this.projectService.addProject(this.projectForm).subscribe(success => {
      this.resetForm();
      if (success) {
        this.fetch();
        this.snackbarService.openSnackbar('Proyecto guardado satisfactoriamente', 'Crear Métricas', '/metric/new');
      } else {
        this.snackbarService.openSnackbar('No se ha podido guardar los cambios');
      }
    });
  }

  private resetForm(): void {
    this.projectForm = ProjectForm.empty();
  }
}
