import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayerListComponent } from './components/player-list/player-list.component';

import { LevelComponent } from './components/level/level.component';
import { NewGameComponent } from './components/line-puzzle/new-game.component';



const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
 { path: 'newGame', component: NewGameComponent },
  { path: 'bestScores', component: PlayerListComponent }
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
