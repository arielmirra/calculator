

export class Calculable {

  public static empty(): Calculable {
    return new Calculable(0, '', null, null, null, null);
  }

  constructor(public id: number,
              public name: string,
              public left?: Calculable,
              public right?: Calculable,
              public operator?: Operator,
              public value?: number) {

  }
}

export enum Operator {
  PLUS,
  MINUS,
  TIMES,
  DIVIDE
}

export class CalculableForm {

  public static empty(): CalculableForm {
    return new CalculableForm('', null, null, null, null);
  }

  constructor(public name: string,
              public left?: Calculable,
              public right?: Calculable,
              public operator?: Operator,
              public value?: number) {

  }
}
