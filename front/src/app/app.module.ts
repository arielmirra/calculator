import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CalculableFormComponent} from './views/calculable-form/calculable-form.component';
import {NavbarComponent} from './views/navbar/navbar.component';
import {CalculableListComponent} from './views/calculable-list/calculable-list.component';
import {AppMaterialModule} from './app-material.module';
import {CommonModule} from '@angular/common';
import { MetricFormComponent } from './views/metric-form/metric-form.component';
import { MetricListComponent } from './views/metric-list/metric-list.component';
import { MetricMeasurementModalComponent } from './views/metric-list/metric-measurement-modal/metric-measurement-modal.component';
import { ProjectFormComponent } from './views/project-form/project-form.component';
import { ProjectListComponent } from './views/project-list/project-list.component';

@NgModule({
  declarations: [
    AppComponent,
    CalculableFormComponent,
    NavbarComponent,
    CalculableListComponent,
    MetricFormComponent,
    MetricListComponent,
    MetricMeasurementModalComponent,
    ProjectFormComponent,
    ProjectListComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppMaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
