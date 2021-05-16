import { Injectable } from '@angular/core';
import {HttpService} from './http.service';
import {Observable, of} from 'rxjs';
import {Measurement} from '../models/Measurement';
import {catchError, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MeasurementService {

  private basePath = '/measurement/';

  constructor(private http: HttpService) {
  }

  getMeasurement(id: number): Observable<Measurement> {
    return this.http.get(this.basePath + id)
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

  getMeasurementFromMetric(metricId: number): Observable<Measurement[]> {
    return this.http.get(this.basePath + 'from/' + metricId)
      .pipe(
        map((response) => {
          return response.body.map(a => Object.assign(Measurement.empty(), a));
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  deleteMeasurement(id: number): Observable<boolean> {
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

  fetchAll(): Observable<Measurement[]> {
    return this.http.get(this.basePath)
      .pipe(
        map(response => {
          return response.body.map(a => Object.assign(Measurement.empty(), a));
        }),
        catchError(err => {
          console.log(err);
          return of([]);
        })
      );
  }
}
