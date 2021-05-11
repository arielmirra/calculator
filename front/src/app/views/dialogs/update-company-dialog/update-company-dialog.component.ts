import {Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Company, CompanyForm} from '../../../models/Company';
import {Project} from '../../../models/Project';
import {MatChipInputEvent} from '@angular/material/chips';
import {MatAutocomplete, MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-update-company-dialog',
  templateUrl: './update-company-dialog.component.html',
  styleUrls: ['./update-company-dialog.component.scss']
})
export class UpdateCompanyDialogComponent implements OnInit {

  company: Company;
  companyForm: CompanyForm;
  everyProject: Project[];
  selectedProjects: Project[];

  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  projectCtrl = new FormControl();
  filteredProjects: Observable<Project[]>;

  @ViewChild('projectsInput') projectsInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;

  constructor(
    public dialogRef: MatDialogRef<UpdateCompanyDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
  ) { }

  ngOnInit(): void {
    this.company = this.data.actual;
    this.everyProject = this.data.projects;
    this.companyForm = CompanyForm.empty();
    this.companyForm.id = this.company._id;
    this.companyForm.name = this.company.name;
    this.companyForm.description = this.company.description;
    this.companyForm.projects = this.company.projects.map(c => c._id);

    this.selectedProjects = this.everyProject.filter(m => this.companyForm.projects.indexOf(m._id) > -1);
    this.filteredProjects = this.projectCtrl.valueChanges.pipe(
      startWith(null),
      map((m: string | null) => m ? this._filter(m) : this.left()));
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isValid(): boolean {
    return this.companyForm.name !== null && this.companyForm.description !== null;
  }

  forceChange(value: string): void{
    this.projectCtrl.setValue(value);
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      const val = this.everyProject.filter(m => m.name + '' === value.trim())[0];
      if (val){
        this.selectedProjects.push(val);
      }
    }
    if (input) {
      input.value = '';
    }
    this.companyForm.projects = this.selectedProjects.map(m => m._id);
    this.projectCtrl.setValue(null);
  }

  remove(project: number): void {
    const index = this.selectedProjects.indexOf(this.everyProject.filter(p => p._id === project)[0]);

    if (index >= 0) {
      this.selectedProjects.splice(index, 1);
    }
    this.companyForm.projects = this.selectedProjects.map(p => p._id);
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.selectedProjects.push(this.everyProject.filter(p => p.name === event.option.value)[0]);
    this.projectsInput.nativeElement.value = '';
    this.projectCtrl.setValue(null);
    this.companyForm.projects = this.selectedProjects.map(p => p._id);
  }

  private _filter(value: string): Project[] {
    const filterValue = value.toLowerCase();
    return this.left().filter(m => m.name.toLowerCase().indexOf(filterValue) === 0);
  }

  private left(): Project[] {
    return this.everyProject.filter(m => this.selectedProjects.indexOf(m) < 0);
  }

}
