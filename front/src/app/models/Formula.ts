import {Calculable, Operator} from './Calculable';

export class Formula {

  public static empty(): Formula {
    return new Formula(-1, Calculable.empty(), new Map<string, number>());
  }

  constructor(
    public id: number,
    public calcTree: Calculable,
    public variables: Map<string, number>) {
  }
}
