import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {AppMaterialModule} from "./app.material-module";
import {ScoreListComponent} from './components/score-list/score-list.component';
import {NewGameComponent} from './components/new-game/new-game.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatSortModule} from '@angular/material/sort';
import {FlexLayoutModule} from '@angular/flex-layout';
import { AdminListComponent, DialogComponent, DialogComponentRole } from './components/admin-list/admin-list.component';
import { MatDialogModule, MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material/dialog';
import { LoginComponent } from './components/login/login.component';
import { HttpInterceptorService } from './services/http-interceptor/http-interceptor-service.service';
import { CommonModule } from '@angular/common';
import { AuthDialogComponent } from './auth/auth.guard';
import { MessageComponent, RegisterComponent } from './components/register/register/register.component';



@NgModule({
  declarations: [
    AppComponent,
    ScoreListComponent,
    NewGameComponent,
    DialogComponent,
    DialogComponentRole,
    MessageComponent,
    LoginComponent,
    AdminListComponent,
    AuthDialogComponent,
    RegisterComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AppMaterialModule,
    BrowserAnimationsModule,
    MatSortModule,
    FlexLayoutModule,
    MatDialogModule,
    CommonModule
  ],

  entryComponents: [
    DialogComponent,
    DialogComponentRole,
    AuthDialogComponent
  ],


  providers: [
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}},
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
