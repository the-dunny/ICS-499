import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppMaterialModule } from "./app.material-module";
import { PlayerListComponent } from './components/player-list/player-list.component';
import { LevelComponent } from './components/level/level.component';
import { NewGameComponent } from './components/new-game/new-game.component';
import {BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSortModule} from '@angular/material/sort';




@NgModule({
  declarations: [
    AppComponent,
    PlayerListComponent,
    LevelComponent,
    NewGameComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AppMaterialModule,
    BrowserAnimationsModule,
    MatSortModule
  ],


  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
