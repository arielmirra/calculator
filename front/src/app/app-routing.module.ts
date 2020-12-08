import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CalculableFormComponent} from './views/calculable-form/calculable-form.component';
import {CalculableListComponent} from './views/calculable-list/calculable-list.component';


export const routes: Routes = [
  {
    path: '',
    component: CalculableFormComponent
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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
