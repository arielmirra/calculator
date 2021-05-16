import {Metric} from './Metric';

export class Measurement {

  public static empty(): Measurement {
    return new Measurement(-1, '', -1, new Date(), Metric.empty(), -1);
  }

  constructor(
    public id: number,
    public name: string,
    public value: number,
    public date: Date,
    public from: Metric,
    public metricId: number,
  ) {

  }
}
