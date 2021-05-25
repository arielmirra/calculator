import { Component, OnInit } from '@angular/core';
import {SnackbarService} from '../../../services/snackbar.service';
import {MatDialog} from '@angular/material/dialog';
import {Router} from '@angular/router';
import {Project} from '../../../models/Project';
import {ProjectService} from '../../../services/project.service';
import {UpdateProjectDialogComponent} from '../../dialogs/update-project-dialog/update-project-dialog.component';
import {MetricService} from '../../../services/metric.service';
import {Metric} from '../../../models/Metric';
import {Measurement} from '../../../models/Measurement';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.scss']
})
export class ProjectListComponent implements OnInit {
  projects: Project[];
  metrics: Metric[];
  lastMeasurement?: {value: Measurement, of: Metric};

  constructor(
    private projectService: ProjectService,
    private metricService: MetricService,
    private snackbar: SnackbarService,
    public dialog: MatDialog,
    private router: Router) {
  }

  ngOnInit(): void {
    this.fetch();
  }

  fetch(): void {
    this.projectService.fetchAll().subscribe(list => {
      console.log(list);
      this.projects = list;
    });
    this.metricService.fetchAll().subscribe(list => {
      this.metrics = list;
    });
  }

  createProject(): void {
    this.router.navigate(['project/new']);
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
          this.snackbar.openSnackbar('Cálculo guardado satisfactoriamente');
          this.fetch();
        } else {
          this.snackbar.openSnackbar('No se ha podido guardar los cambios');
        }
      });
    });
  }

}
