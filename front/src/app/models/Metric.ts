import {Calculable} from './Calculable';

export class Metric {

  public static empty(): Metric {
    return new Metric(-1, '', '', [], []);
  }

  constructor(
    public id: number,
    public name: string,
    public description: string,
    public metrics: Metric[],
    public calculates: Calculable[]
  ) {

  }
}

export class MetricForm {

  public static empty(): MetricForm {
    return new MetricForm('', '', null, null);
  }

  constructor(
    public name: string,
    public description: string,
    public id?: number,
    public metrics?: number[],
    public calculates?: number[]
  ) {

  }
}
