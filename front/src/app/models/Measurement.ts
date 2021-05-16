export class Measurement {

  public static empty(): Measurement {
    return new Measurement(-1, '', -1, new Date());
  }

  constructor(
    public id: number,
    public name: string,
    public value: number,
    public date: Date,
  ) {

  }
}
