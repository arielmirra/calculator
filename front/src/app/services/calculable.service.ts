import {Injectable} from '@angular/core';
import {HttpService} from './http.service';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Calculable, CalculableForm} from '../models/Calculable';

@Injectable({
  providedIn: 'root'
})
export class CalculableService {

  private basePath = '/calculable/';

  constructor(private http: HttpService) {
  }

  getCalculable(id: number): Observable<Calculable> {
    return this.http.get(this.basePath + id)
      .pipe(
        map((response) => {
          return Object.assign(Calculable.empty(), response.body);
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getCalculableByName(name: string): Observable<Calculable> {
    return this.http.get(this.basePath + 'name/' + name)
      .pipe(
        map((response) => {
          return Object.assign(Calculable.empty(), response.body);
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  addCalculable(form: CalculableForm): Observable<boolean> {
    return this.http.post(this.basePath, form)
      .pipe(
        map(() => true),
        catchError(err => {
          console.log(err);
          return of(false);
        })
      );
  }

  updateCalculable(form: CalculableForm, id: number): Observable<boolean> {
    return this.http.put(this.basePath + id, form)
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

  deleteCalculable(id: number): Observable<boolean> {
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

  fetchAll(): Observable<Calculable[]> {
    return this.http.get(this.basePath)
      .pipe(
        map(response => {
          return response.body.map(a => Object.assign(Calculable.empty(), a));
        }),
        catchError(err => {
          console.log(err);
          return of([]);
        })
      );
  }

  calculate(_id: number): Observable<number> {
    return this.http.get(`${this.basePath}calculate/${_id}`)
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }
}
