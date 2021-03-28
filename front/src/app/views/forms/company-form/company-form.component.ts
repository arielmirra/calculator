import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {SnackbarService} from '../../../services/snackbar.service';
import {ProjectService} from '../../../services/project.service';
import {Project} from '../../../models/Project';
import {CompanyForm} from '../../../models/Company';
import {CompanyService} from '../../../services/company.service';

@Component({
  selector: 'app-company-form',
  templateUrl: './company-form.component.html',
  styleUrls: ['./company-form.component.scss']
})
export class CompanyFormComponent implements OnInit {
  form: FormGroup;
  projects: Project[];
  selectedProjects: number[] = [];

  constructor(
    private router: Router,
    private snackbarService: SnackbarService,
    private projectService: ProjectService,
    private companyService: CompanyService,
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
    this.projectService.fetchAll().subscribe(list => this.projects = list);
  }

  hasError(controlName: string, errorName: string): boolean {
    return this.form.controls[controlName].hasError(errorName);
  }

  newProject(formDirective: FormGroupDirective): void {
    const form = CompanyForm.empty();
    form.name = this.form.controls.name.value;
    form.description = this.form.controls.description.value;
    form.projects = this.selectedProjects;
    console.log(form);
    this.companyService.addCompany(form).subscribe(success => {
      this.resetForm(formDirective);
      if (success) {
        this.fetch();
        this.snackbarService.openSnackbar('Empresa guardada satisfactoriamente');
      } else {
        this.snackbarService.openSnackbar('No se ha podido guardar los cambios');
      }
    });
  }


  private resetForm(formDirective: FormGroupDirective): void {
    this.form.reset();
    formDirective.resetForm();
    this.selectedProjects = [];
  }

}
