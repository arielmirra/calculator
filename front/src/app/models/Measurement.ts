import {Calculable} from './Calculable';

export class Measurement {

  public static empty(): Measurement {
    return new Measurement(-1, -1, new Date(), Calculable.empty());
  }

  constructor(
    public id: number,
    public value: number,
    public date: Date,
    public from: Calculable,
  ) {

  }
}
