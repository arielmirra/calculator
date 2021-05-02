import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Company, CompanyForm} from '../../../models/Company';
import {Project} from '../../../models/Project';

@Component({
  selector: 'app-update-company-dialog',
  templateUrl: './update-company-dialog.component.html',
  styleUrls: ['./update-company-dialog.component.scss']
})
export class UpdateCompanyDialogComponent implements OnInit {

  company: Company;
  companyForm: CompanyForm;
  everyProject: Project[];

  constructor(
    public dialogRef: MatDialogRef<UpdateCompanyDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
  ) { }

  ngOnInit(): void {
    console.log(this.data.actual);
    console.log(this.data.calculables);
    this.company = this.data.actual;
    this.everyProject = this.data.projects;
    this.companyForm = CompanyForm.empty();
    this.companyForm.id = this.company._id;
    this.companyForm.name = this.company.name;
    this.companyForm.description = this.company.description;
    this.companyForm.projects = this.company.projects.map(c => c._id);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isValid(): boolean {
    return this.companyForm.name !== null && this.companyForm.description !== null;
  }

}
