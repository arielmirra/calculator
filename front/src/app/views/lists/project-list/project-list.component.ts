import { Component, OnInit } from '@angular/core';
import {SnackbarService} from '../../../services/snackbar.service';
import {MatDialog} from '@angular/material/dialog';
import {Router} from '@angular/router';
import {Project} from '../../../models/Project';
import {ProjectService} from '../../../services/project.service';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.scss']
})
export class ProjectListComponent implements OnInit {
  projects: Project[];

  constructor(
    private projectService: ProjectService,
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
  }

  createProject(): void {
    this.router.navigate(['project/new']);
  }

}
