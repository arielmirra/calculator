import {Calculable} from './Calculable';

export class Metric {

  public static empty(): Metric {
    return new Metric(0, 'Metric', '', '', [], []);
  }

  constructor(
    public _id: number,
    public _type: string,
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
    public metrics?: any[],
    public calculates?: any[]
  ) {

  }
}
