import {Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import {Metric, MetricForm} from '../../../models/Metric';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Project, ProjectForm} from '../../../models/Project';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {MatAutocomplete, MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {MatChipInputEvent} from '@angular/material/chips';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-update-project-dialog',
  templateUrl: './update-project-dialog.component.html',
  styleUrls: ['./update-project-dialog.component.scss']
})
export class UpdateProjectDialogComponent implements OnInit {

  project: Project;
  projectForm: ProjectForm;
  everyMetric: Metric[];
  selectedMetrics: Metric[];

  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  metricCtrl = new FormControl();
  filteredMetrics: Observable<Metric[]>;

  @ViewChild('metricsInput') metricsInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;

  constructor(
    public dialogRef: MatDialogRef<UpdateProjectDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
  ) {}

  ngOnInit(): void {
    console.log(this.data.actual);
    this.project = this.data.actual;
    this.everyMetric = this.data.metrics;
    this.projectForm = MetricForm.empty();
    this.projectForm.id = this.project.id;
    this.projectForm.name = this.project.name;
    this.projectForm.description = this.project.description;
    this.projectForm.calculables = this.project.calculables.map(c => c.id);

    this.selectedMetrics = this.everyMetric.filter(m => this.projectForm.calculables.indexOf(m.id) > -1);
    this.filteredMetrics = this.metricCtrl.valueChanges.pipe(
      startWith(null),
      map((m: string | null) => m ? this._filter(m) : this.left()));
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isValid(): boolean {
    return this.projectForm.name !== null && this.projectForm.description !== null;
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

  private _filter(value: string): Metric[] {
    const filterValue = value.toLowerCase();
    return this.left().filter(m => m.name.toLowerCase().indexOf(filterValue) === 0);
  }

  private left(): Metric[] {
    return this.everyMetric.filter(m => this.selectedMetrics.indexOf(m) < 0);
  }
}
