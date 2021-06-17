import { Injectable } from '@angular/core';
import {HttpService} from './http.service';
import {Observable, of} from 'rxjs';
import {Formula} from '../models/Formula';
import {catchError, map} from 'rxjs/operators';
import {Measurement} from '../models/Measurement';

@Injectable({
  providedIn: 'root'
})
export class FormulaService {

  private basePath = '/formula/';

  constructor(private http: HttpService) {
  }

  getFormula(id: number): Observable<Formula> {
    return this.http.get(this.basePath + id)
      .pipe(
        map((response) => {
          return Object.assign(Formula.empty(), response.body);
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  measure(id: number, formula: Formula): Observable<Measurement> {
    return this.http.put(this.basePath + id, formula)
      .pipe(
        map((response) => {
          return Object.assign(Measurement.empty(), response.body);
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getFormulaFromCalculable(calcId: number): Observable<Formula[]> {
    return this.http.get(this.basePath + 'from/' + calcId)
      .pipe(
        map((response) => {
          return response.body.map(a => Object.assign(Formula.empty(), a));
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  deleteFormula(id: number): Observable<boolean> {
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

  fetchAll(): Observable<Formula[]> {
    return this.http.get(this.basePath)
      .pipe(
        map(response => {
          return response.body.map(a => Object.assign(Formula.empty(), a));
        }),
        catchError(err => {
          console.log(err);
          return of([]);
        })
      );
  }
}
