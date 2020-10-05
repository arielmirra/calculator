import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CalculableFormComponent} from './views/calculable-form/calculable-form.component';


export const routes: Routes = [
  {
    path: '',
    component: CalculableFormComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
