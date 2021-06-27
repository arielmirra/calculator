import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {SnackbarService} from '../../../services/snackbar.service';
import {ProjectService} from '../../../services/project.service';
import {Project} from '../../../models/Project';
import {Company, CompanyForm} from '../../../models/Company';
import {CompanyService} from '../../../services/company.service';
import {map, startWith} from 'rxjs/operators';
import {MatChipInputEvent} from '@angular/material/chips';
import {MatAutocomplete, MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-company-form',
  templateUrl: './company-form.component.html',
  styleUrls: ['./company-form.component.scss']
})
export class CompanyFormComponent implements OnInit {

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
    private router: Router,
    private snackbarService: SnackbarService,
    private projectService: ProjectService,
    private companyService: CompanyService,
  ) {
  }

  ngOnInit(): void {
    this.company = Company.empty();
    this.companyForm = CompanyForm.empty();
    this.fetch();
    this.companyForm.id = this.company.id;
    this.companyForm.name = this.company.name;
    this.companyForm.description = this.company.description;
    this.companyForm.projects = this.company.projects.map(c => c.id);
  }

  fetch(): void {
    this.projectService.fetchAll().subscribe(list => {
      this.everyProject = list;
      this.selectedProjects = this.everyProject.filter(m => this.companyForm.projects.indexOf(m.id) > -1);
      this.filteredProjects = this.projectCtrl.valueChanges.pipe(
        startWith(null),
        map((m: string | null) => m ? this._filter(m) : this.left()));
    });
  }

  isValid(): boolean {
    return (this.companyForm.name !== '' && this.companyForm.description !== '');
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
    this.companyForm.projects = this.selectedProjects.map(m => m.id);
    this.projectCtrl.setValue(null);
  }

  remove(project: number): void {
    const index = this.selectedProjects.indexOf(this.everyProject.filter(p => p.id === project)[0]);

    if (index >= 0) {
      this.selectedProjects.splice(index, 1);
    }
    this.companyForm.projects = this.selectedProjects.map(p => p.id);
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.selectedProjects.push(this.everyProject.filter(p => p.name === event.option.value)[0]);
    this.projectsInput.nativeElement.value = '';
    this.projectCtrl.setValue(null);
    this.companyForm.projects = this.selectedProjects.map(p => p.id);
  }

  private _filter(value: string): Project[] {
    const filterValue = value.toLowerCase();
    return this.left().filter(m => m.name.toLowerCase().indexOf(filterValue) === 0);
  }

  private left(): Project[] {
    return this.everyProject.filter(m => this.selectedProjects.indexOf(m) < 0);
  }

  newProject(): void {
    this.companyService.addCompany(this.companyForm).subscribe(success => {
      this.resetForm();
      if (success) {
        this.fetch();
        this.snackbarService.openSnackbar('Empresa guardada satisfactoriamente', 'Crear Proyectos', '/project/new/');
      } else {
        this.snackbarService.openSnackbar('No se ha podido guardar los cambios');
      }
    });
  }


  private resetForm(): void {
    this.company = Company.empty();
    this.companyForm = CompanyForm.empty();
    this.selectedProjects = [];
  }

}
