import {Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import {Calculable, CalculableForm} from '../../../models/Calculable';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {MatChipInputEvent} from '@angular/material/chips';
import {MatAutocomplete, MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';

const CALCULABLE_TYPE = 1;

@Component({
  selector: 'app-update-metric-dialog',
  templateUrl: './update-metric-dialog.component.html',
  styleUrls: ['./update-metric-dialog.component.scss']
})
export class UpdateMetricDialogComponent implements OnInit {

  metric: Calculable;
  metricForm: CalculableForm;
  everyCalculable: Calculable[];
  everyMetric: Calculable[];

  selectedCalculables: Calculable[];
  selectedMetrics: Calculable[];

  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  calculableCtrl = new FormControl();
  metricCtrl = new FormControl();
  filteredCalculables: Observable<Calculable[]>;
  filteredMetrics: Observable<Calculable[]>;

  @ViewChild('metricsInput') metricsInput: ElementRef<HTMLInputElement>;
  @ViewChild('calculablesInput') calculablesInput: ElementRef<HTMLInputElement>;
  @ViewChild('autom') matAutocompleteM: MatAutocomplete;
  @ViewChild('autoc') matAutocompleteC: MatAutocomplete;

  constructor(
    public dialogRef: MatDialogRef<UpdateMetricDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
  ) { }

  ngOnInit(): void {
    this.metric = this.data.actual;
    this.everyMetric = this.data.metrics.filter(c => c.id !== this.data.actual.id);
    this.everyCalculable = this.data.calculables;
    this.metricForm = CalculableForm.empty();
    this.metricForm.id = this.metric.id;
    this.metricForm.name = this.metric.name;
    this.metricForm.description = this.metric.description;
    this.metricForm.left = this.metric.left ? this.metric.left.id : null;
    this.metricForm.right = this.metric.right ? this.metric.right.id : null;
    this.metricForm.operator = this.metric.operator;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  //
  // isValid(): boolean {
  //   return this.metricForm.name !== null && this.metricForm.description !== null;
  // }
  //
  // forceChange(value: string): void{
  //   this.metricCtrl.setValue(value);
  // }

  // add(event: MatChipInputEvent, type: number): void {
  //   const input = event.input;
  //   const value = event.value;
  //
  //   if (type === CALCULABLE_TYPE) {
  //     if ((value || '').trim()) {
  //       const val = this.everyCalculable.filter(c => c.name + '' === value.trim())[0];
  //       if (val){
  //         this.selectedCalculables.push(val);
  //       }
  //     }
  //     // Reset the input value
  //     if (input) {
  //       input.value = '';
  //     }
  //     this.metricForm.calculates = this.selectedCalculables.map(m => m.id);
  //     this.calculableCtrl.setValue(null);
  //   } else {
  //     if ((value || '').trim()) {
  //       const val = this.everyMetric.filter(m => m.name + '' === value.trim())[0];
  //       if (val){
  //         this.selectedMetrics.push(val);
  //       }
  //     }
  //
  //     // Reset the input value
  //     if (input) {
  //       input.value = '';
  //     }
  //     this.metricForm.metrics = this.selectedMetrics.map(m => m.id);
  //     this.metricCtrl.setValue(null);
  //   }
  // }

  // remove(removable: number, type: number): void {
  //   if (type === CALCULABLE_TYPE) {
  //     this.removeCalculable(removable);
  //   } else {
  //     this.removeMetric(removable);
  //   }
  // }
  //
  // private removeMetric(metric: number): void {
  //   const index = this.selectedMetrics.indexOf(this.everyMetric.filter(m => m.id === metric)[0]);
  //
  //   if (index >= 0) {
  //     this.selectedMetrics.splice(index, 1);
  //   }
  //   this.metricForm.metrics = this.selectedMetrics.map(m1 => m1.id);
  // }
  //
  // private removeCalculable(calc: number): void {
  //   const index = this.selectedCalculables.indexOf(this.everyCalculable.filter(c => c.id === calc)[0]);
  //
  //   if (index >= 0) {
  //     this.selectedCalculables.splice(index, 1);
  //   }
  //   this.metricForm.calculates = this.selectedCalculables.map(c => c.id);
  // }

  // selectedMetric(event: MatAutocompleteSelectedEvent): void {
  //   this.selectedMetrics.push(this.everyMetric.filter(m => m.name === event.option.value)[0]);
  //   this.metricsInput.nativeElement.value = '';
  //   this.metricCtrl.setValue(null);
  //   this.metricForm.metrics = this.selectedMetrics.map(m1 => m1.id);
  // }
  //
  // selectedCalculable(event: MatAutocompleteSelectedEvent): void {
  //   this.selectedCalculables.push(this.everyCalculable.filter(c => c.name === event.option.value)[0]);
  //   this.calculablesInput.nativeElement.value = '';
  //   this.calculableCtrl.setValue(null);
  //   this.metricForm.calculates = this.selectedCalculables.map(c => c.id);
  // }
  //
  // private _filterMetrics(value: string): Metric[] {
  //   const filterValue = value.toLowerCase();
  //   return this.metricsLeft().filter(m => m.name.toLowerCase().indexOf(filterValue) === 0);
  // }
  //
  // private _filterMCalculables(value: string): Calculable[] {
  //   const filterValue = value.toLowerCase();
  //   return this.calclablesLeft().filter(c => c.name.toLowerCase().indexOf(filterValue) === 0);
  // }
  //
  // private metricsLeft(): Metric[] {
  //   return this.everyMetric.filter(m => this.selectedMetrics.indexOf(m) < 0);
  // }
  //
  // private calclablesLeft(): Calculable[] {
  //   return this.everyCalculable.filter(c => this.selectedCalculables.indexOf(c) < 0);
  // }

}
