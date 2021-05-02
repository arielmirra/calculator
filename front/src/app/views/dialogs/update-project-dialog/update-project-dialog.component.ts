import {Component, Inject, OnInit} from '@angular/core';
import {Metric, MetricForm} from '../../../models/Metric';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Project, ProjectForm} from '../../../models/Project';

@Component({
  selector: 'app-update-project-dialog',
  templateUrl: './update-project-dialog.component.html',
  styleUrls: ['./update-project-dialog.component.scss']
})
export class UpdateProjectDialogComponent implements OnInit {

  project: Project;
  projectForm: ProjectForm;
  everyMetric: Metric[];

  constructor(
    public dialogRef: MatDialogRef<UpdateProjectDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
  ) { }

  ngOnInit(): void {
    console.log(this.data.actual);
    this.project = this.data.actual;
    this.everyMetric = this.data.metrics;
    this.projectForm = MetricForm.empty();
    this.projectForm.id = this.project._id;
    this.projectForm.name = this.project.name;
    this.projectForm.description = this.project.description;
    this.projectForm.metrics = this.project.metrics.map(c => c._id);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isValid(): boolean {
    return this.projectForm.name !== null && this.projectForm.description !== null;
  }

}
