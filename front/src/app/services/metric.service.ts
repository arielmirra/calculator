import { Injectable } from '@angular/core';
import {HttpService} from './http.service';
import {Observable, of} from 'rxjs';
import {Measurement, Metric, MetricForm} from '../models/Metric';
import {catchError, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MetricService {

  private basePath = '/metric/';

  constructor(private http: HttpService) {
  }

  getMetric(id: number): Observable<Metric> {
    return this.http.get(this.basePath + id)
      .pipe(
        map((response) => {
          return Object.assign(Metric.empty(), response.body);
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  getMetricByName(name: string): Observable<Metric> {
    return this.http.get(this.basePath + 'name/' + name)
      .pipe(
        map((response) => {
          return Object.assign(Metric.empty(), response.body);
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      );
  }

  addMetric(form: MetricForm): Observable<boolean> {
    return this.http.post(this.basePath, form)
      .pipe(
        map(() => true),
        catchError(err => {
          console.log(err);
          return of(false);
        })
      );
  }

  updateMetric(form: MetricForm): Observable<boolean> {
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

  deleteMetric(id: number): Observable<boolean> {
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

  fetchAll(): Observable<Metric[]> {
    return this.http.get(this.basePath)
      .pipe(
        map(response => {
          const metrics = response.body.map(a => Object.assign(Metric.empty(), a));
          return metrics[0]._id !== -1 ? metrics : [];
        }),
        catchError(err => {
          console.log(err);
          return of([]);
        })
      );
  }

  fetchAllComplete(): Observable<Metric[]> {
    return this.http.get(this.basePath)
      .pipe(
        map(response => {
          return response.body.map(a => Object.assign(Metric.empty(), a));
        }),
        catchError(err => {
          console.log(err);
          return of([]);
        })
      );
  }

  measure(id: number): Observable<Measurement> {
    return this.http.get(`${this.basePath}measure/${id}`)
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
