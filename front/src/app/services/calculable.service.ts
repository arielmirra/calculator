import {Injectable} from '@angular/core';
import {HttpService} from './http.service';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Calculable, CalculableForm} from '../models/Calculable';

@Injectable({
  providedIn: 'root'
})
export class CalculableService {

  constructor(private http: HttpService) {
  }

  getCalculable(id: number): Observable<Calculable> {
    return this.http.get('/calculable/' + id)
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
    return this.http.get('/calculable/name/' + name)
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
    return this.http.post('/calculable/', form)
      .pipe(
        map(() => true),
        catchError(err => {
          console.log(err);
          return of(false);
        })
      );
  }

  updateCalculable(form: CalculableForm): Observable<boolean> {
    return this.http.put('/calculable/', form)
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
    return this.http.delete('/calculable/' + id)
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
}
