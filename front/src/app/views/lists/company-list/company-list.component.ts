import { Component, OnInit } from '@angular/core';
import {SnackbarService} from '../../../services/snackbar.service';
import {MatDialog} from '@angular/material/dialog';
import {Router} from '@angular/router';
import {Company} from '../../../models/Company';
import {CompanyService} from '../../../services/company.service';
import {UpdateCompanyDialogComponent} from '../../dialogs/update-company-dialog/update-company-dialog.component';
import {Project} from '../../../models/Project';
import {ProjectService} from '../../../services/project.service';

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.scss']
})
export class CompanyListComponent implements OnInit {
  companies: Company[];
  projects: Project[];

  constructor(
    private companyService: CompanyService,
    private projectService: ProjectService,
    private snackbar: SnackbarService,
    public dialog: MatDialog,
    private router: Router) {
  }

  ngOnInit(): void {
    this.fetch();
  }

  fetch(): void {
    this.companyService.fetchAll().subscribe(list => {
      console.log(list);
      this.companies = list;
    });
    this.projectService.fetchAll().subscribe(list => {
      this.projects = list;
    });
  }

  createCompany(): void {
    this.router.navigate(['company/new']);
  }

  seeProjects(company: Company): void {
    this.router.navigate(['company/projects/' + company.id]);
  }

  openDialog(company: Company): void {
    const dialogRef = this.dialog.open(UpdateCompanyDialogComponent, {
      width: '400px',
      data: {actual: company, projects: this.projects}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.companyService.updateCompany(result).subscribe(success => {
        if (success) {
          this.snackbar.openSnackbar('Empresa guardada satisfactoriamente');
          this.fetch();
        } else {
          this.snackbar.openSnackbar('No se ha podido guardar los cambios');
        }
      });
    });
  }

}
