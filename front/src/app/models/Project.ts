import {Metric} from './Metric';

export class Project {

  public static empty(): Project {
    return new Project(-1, 'Project', '', '', []);
  }

  constructor(
    public _id: number,
    public _type: string,
    public name: string,
    public description: string,
    public measurements: Metric[],
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
    public measurements?: number[]
  ) {

  }
}