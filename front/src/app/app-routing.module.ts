import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CalculableFormComponent} from './views/forms/calculable-form/calculable-form.component';
import {VariableListComponent} from './views/lists/variable-list/variable-list.component';
import {MetricFormComponent} from './views/forms/metric-form/metric-form.component';
import {MetricListComponent} from './views/lists/metric-list/metric-list.component';
import {ProjectFormComponent} from './views/forms/project-form/project-form.component';
import {ProjectListComponent} from './views/lists/project-list/project-list.component';
import {CompanyFormComponent} from './views/forms/company-form/company-form.component';
import {CompanyListComponent} from './views/lists/company-list/company-list.component';
import {MetricsOfProjectListComponent} from './views/lists/metrics-of-project-list/metrics-of-project-list.component';
import {MetricsOfMetricsListComponent} from './views/lists/metrics-of-metrics-list/metrics-of-metrics-list.component';
import {ProjectsOfCompanyListComponent} from './views/lists/projects-of-company-list/projects-of-company-list.component';

export const routes: Routes = [
  {
    path: '',
    component: VariableListComponent
  },
  {
    path: 'variable',
    children: [
      {
        path: 'new',
        component: CalculableFormComponent
      },
      {
        path: 'list',
        component: VariableListComponent
      }
    ]
  },
  {
    path: 'metric',
    children: [
      {
        path: 'new',
        component: MetricFormComponent
      },
      {
        path: 'list',
        component: MetricListComponent
      },
      {
        path: 'metrics/:id',
        component: MetricsOfMetricsListComponent
      }
    ]
  },
  {
    path: 'project',
    children: [
      {
        path: 'new',
        component: ProjectFormComponent
      },
      {
        path: 'list',
        component: ProjectListComponent
      },
      {
        path: 'metrics/:id',
        component: MetricsOfProjectListComponent
      }
    ]
  },
  {
    path: 'company',
    children: [
      {
        path: 'new',
        component: CompanyFormComponent
      },
      {
        path: 'list',
        component: CompanyListComponent
      },
      {
        path: 'projects/:id',
        component: ProjectsOfCompanyListComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
