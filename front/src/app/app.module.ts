import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CalculableFormComponent} from './views/forms/calculable-form/calculable-form.component';
import {NavbarComponent} from './views/navbar/navbar.component';
import {CalculableListComponent} from './views/lists/calculable-list/calculable-list.component';
import {AppMaterialModule} from './app-material.module';
import {CommonModule} from '@angular/common';
import { MetricFormComponent } from './views/forms/metric-form/metric-form.component';
import { MetricListComponent } from './views/lists/metric-list/metric-list.component';
import { MetricMeasurementModalComponent } from './views/lists/metric-list/metric-measurement-modal/metric-measurement-modal.component';
import { ProjectFormComponent } from './views/forms/project-form/project-form.component';
import { ProjectListComponent } from './views/lists/project-list/project-list.component';
import { CompanyFormComponent } from './views/forms/company-form/company-form.component';
import { CompanyListComponent } from './views/lists/company-list/company-list.component';
import { UpdateCalculableDialogComponent } from './views/dialogs/update-calculable-dialog/update-calculable-dialog.component';
import { UpdateMetricDialogComponent } from './views/dialogs/update-metric-dialog/update-metric-dialog.component';
import { UpdateProjectDialogComponent } from './views/dialogs/update-project-dialog/update-project-dialog.component';
import { UpdateCompanyDialogComponent } from './views/dialogs/update-company-dialog/update-company-dialog.component';
import { ChartComponent } from './views/chart/chart.component';
import {ChartsModule} from 'ng2-charts';
import { MetricChartComponent } from './views/chart/metric-chart/metric-chart.component';

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
    ProjectListComponent,
    CompanyFormComponent,
    CompanyListComponent,
    UpdateCalculableDialogComponent,
    UpdateMetricDialogComponent,
    UpdateProjectDialogComponent,
    UpdateCompanyDialogComponent,
    ChartComponent,
    MetricChartComponent
  ],
    imports: [
        CommonModule,
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        AppMaterialModule,
        ChartsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
