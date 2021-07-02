import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../../../services/project.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Company} from '../../../models/Company';
import {CompanyService} from '../../../services/company.service';
import {Measurement} from '../../../models/Measurement';
import {Project} from '../../../models/Project';
import {UpdateProjectDialogComponent} from '../../dialogs/update-project-dialog/update-project-dialog.component';
import {SnackbarService} from '../../../services/snackbar.service';
import {MatDialog} from '@angular/material/dialog';
import {Calculable, CalculableType} from '../../../models/Calculable';
import {CalculableService} from '../../../services/calculable.service';

@Component({
  selector: 'app-projects-of-company-list',
  templateUrl: './projects-of-company-list.component.html',
  styleUrls: ['./projects-of-company-list.component.scss']
})
export class ProjectsOfCompanyListComponent implements OnInit {

  company: Company;
  metrics: Calculable[];
  lastMeasurement?: {value: Measurement, of: number};

  constructor(
    private projectService: ProjectService,
    private companyService: CompanyService,
    private calculableService: CalculableService,
    private route: ActivatedRoute,
    private router: Router,
    private snackbar: SnackbarService,
    public dialog: MatDialog,
  ) { }

  ngOnInit(): void {
    const id: number = +this.route.snapshot.paramMap.get('id');
    this.companyService.getCompany(id).subscribe(result => {
      this.company = result;
    });
    this.fetch();
  }

  fetch(): void {
    this.calculableService.fetchAll().subscribe(list =>
      this.metrics = list.filter(i => i.calculableType === CalculableType.METRIC));
  }

  seeMetrics(project: Project): void {
    this.router.navigate(['project/metrics/' + project.id]);
  }

  openDialog(project: Project): void {
    const dialogRef = this.dialog.open(UpdateProjectDialogComponent, {
      width: '400px',
      data: {actual: project, metrics: this.metrics}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.projectService.updateProject(result).subscribe(success => {
        if (success) {
          this.snackbar.openSnackbar('Proyecto guardado satisfactoriamente');
          this.fetch();
        } else {
          this.snackbar.openSnackbar('No se ha podido guardar los cambios');
        }
      });
    });
  }

}
