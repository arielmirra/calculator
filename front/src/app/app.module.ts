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
import { CalculableModalComponent } from './views/calculable-form/calculable-modal/calculable-modal.component';
import {CommonModule} from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    CalculableFormComponent,
    NavbarComponent,
    CalculableListComponent,
    CalculableModalComponent
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
