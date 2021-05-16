import { Injectable } from '@angular/core';
import {HttpService} from './http.service';
import {Observable, of} from 'rxjs';
import {Project, ProjectForm} from '../models/Project';
import {catchError, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private basePath = '/project/';

  constructor(private http: HttpService) {
  }

  getProject(id: number): Observable<Project> {
    return this.http.get(this.basePath + id)
      .pipe(
        map((response) => {
          return Object.assign(Project.empty(), response.body);
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getProjectByName(name: string): Observable<Project> {
    return this.http.get(this.basePath + 'name/' + name)
      .pipe(
        map((response) => {
          return Object.assign(Project.empty(), response.body);
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  addProject(form: ProjectForm): Observable<boolean> {
    return this.http.post(this.basePath, form)
      .pipe(
        map(() => true),
        catchError(err => {
          console.log(err);
          return of(false);
        })
      );
  }

  updateProject(form: ProjectForm): Observable<boolean> {
    return this.http.put(this.basePath + form.id, form)
      .pipe(
        map(() => {
          return true;
        }),
        catchError(err => {
          console.log(err);
          return of(false);
        })
      );
  }

  deleteProject(id: number): Observable<boolean> {
    return this.http.delete(this.basePath + id)
      .pipe(
        map(() => {
          return true;
        }),
        catchError(err => {
          console.log(err);
          return of(false);
        })
      );
  }

  fetchAll(): Observable<Project[]> {
    return this.http.get(this.basePath)
      .pipe(
        map(response => {
          const projects = response.body.map(a => Object.assign(Project.empty(), a));
          return projects[0].id !== -1 ? projects : [];
        }),
        catchError(err => {
          console.log(err);
          return of([]);
        })
      );
  }
}
