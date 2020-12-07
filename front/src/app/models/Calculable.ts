export class Calculable {

  public static empty(): Calculable {
    return new Calculable(0, 'Calculable', '', null, null, null, null);
  }

  constructor(public _id: number,
              public _type: string,
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
              public left?: number, // only id
              public right?: number,
              public operator?: Operator,
              public value?: number) {

  }
}
