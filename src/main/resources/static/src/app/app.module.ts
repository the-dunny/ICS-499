import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppMaterialModule } from "./app.material-module";
import { PlayerListComponent } from './components/player-list/player-list.component';
import { LevelComponent } from './components/level/level.component';
import { NewGameComponent } from './components/line-puzzle/new-game.component';


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
    AppMaterialModule
  ],


  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
