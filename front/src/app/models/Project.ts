import {Calculable} from './Calculable';

export class Project {

  public static empty(): Project {
    return new Project(-1, '', '', []);
  }

  constructor(
    public id: number,
    public name: string,
    public description: string,
    public calculables: Calculable[],
  ) {

  }
}

export class ProjectForm {

  public static empty(): ProjectForm {
    return new ProjectForm('', '', null);
  }

  constructor(
    public name: string,
    public description: string,
    public id?: number,
    public calculables?: number[]
  ) {

  }
}
