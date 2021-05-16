import {Project} from './Project';

export class Company {

  public static empty(): Company {
    return new Company(-1, '', '', []);
  }

  constructor(
    public id: number,
    public name: string,
    public description: string,
    public projects: Project[],
  ) {

  }
}

export class CompanyForm {

  public static empty(): CompanyForm {
    return new CompanyForm('', '', null);
  }

  constructor(
    public name: string,
    public description: string,
    public id?: number,
    public projects?: number[]
  ) {

  }
}
