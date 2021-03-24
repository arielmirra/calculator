import { Injectable } from '@angular/core';
import {HttpService} from './http.service';
import {Observable, of} from 'rxjs';
import {Company, CompanyForm} from '../models/Company';
import {catchError, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private basePath = '/company/';

  constructor(private http: HttpService) {
  }

  getCompany(id: number): Observable<Company> {
    return this.http.get(this.basePath + id)
      .pipe(
        map((response) => {
          return Object.assign(Company.empty(), response.body);
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getCompanyByName(name: string): Observable<Company> {
    return this.http.get(this.basePath + 'name/' + name)
      .pipe(
        map((response) => {
          return Object.assign(Company.empty(), response.body);
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  addCompany(form: CompanyForm): Observable<boolean> {
    return this.http.post(this.basePath, form)
      .pipe(
        map(() => true),
        catchError(err => {
          console.log(err);
          return of(false);
        })
      );
  }

  updateCompany(form: CompanyForm): Observable<boolean> {
    return this.http.put(this.basePath, form)
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

  deleteCompany(id: number): Observable<boolean> {
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

  fetchAll(): Observable<Company[]> {
    return this.http.get(this.basePath)
      .pipe(
        map(response => {
          const companies = response.body.map(a => Object.assign(Company.empty(), a));
          return companies[0]._id !== -1 ? companies : [];
        }),
        catchError(err => {
          console.log(err);
          return of([]);
        })
      );
  }
}
