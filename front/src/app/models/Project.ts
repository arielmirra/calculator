import {Metric} from './Metric';

export class Project {

  public static empty(): Project {
    return new Project(-1, '', '', []);
  }

  constructor(
    public id: number,
    public name: string,
    public description: string,
    public metrics: Metric[],
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
    public metrics?: number[]
  ) {

  }
}
