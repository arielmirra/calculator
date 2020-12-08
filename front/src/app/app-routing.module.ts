import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CalculableFormComponent} from './views/calculable-form/calculable-form.component';
import {CalculableListComponent} from './views/calculable-list/calculable-list.component';
import {MetricFormComponent} from './views/metric-form/metric-form.component';


export const routes: Routes = [
  {
    path: '',
    component: CalculableListComponent
  },
  {
    path: 'calculable',
    children: [
      {
        path: 'new',
        component: CalculableFormComponent
      },
      {
        path: 'list',
        component: CalculableListComponent
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
        component: CalculableListComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
