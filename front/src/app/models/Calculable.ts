export class Calculable {

  constructor(
    public id: number,
    public name: string,
    public description: string,
    public calculableType: CalculableType,
    public children: Calculable[],
    public left?: Calculable,
    public right?: Calculable,
    public operator?: Operator,
    public value?: number) {

  }

  public static empty(): Calculable {
    return new Calculable(-1, '', '', null, [], null, null, null, null);
  }
}

export class CalculableForm {

  public static empty(): CalculableForm {
    return new CalculableForm('', '', CalculableType.VARIABLE, null, null, null, null);
  }

  constructor(
    public name: string,
    public description: string,
    public calculableType: CalculableType,
    public children?: number[],
    public left?: number, // only id
    public id?: number,
    public right?: number,
    public operator?: Operator,
    public value?: number) {

  }
}

export enum Operator {
  PLUS = 'SUMA (+)',
  MINUS = 'RESTA (-)',
  TIMES = 'MULTIPLICACION (*)',
  DIVIDE = 'DIVISION (/)',
  SUMMATION = 'SUMATORIA',
  AVERAGE = 'PROMEDIO',
}

export enum CalculableType {
  METRIC = 'METRIC',
  VARIABLE = 'VARIABLE' ,
  COPY = 'COPY',
}
