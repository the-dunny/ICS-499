import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ScoreListComponent} from './components/score-list/score-list.component';
import {NewGameComponent} from './components/new-game/new-game.component';
import { AdminListComponent } from './components/admin-list/admin-list.component';


const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
 { path: 'newGame', component: NewGameComponent },
  { path: 'bestScores', component: ScoreListComponent },
  { path: 'admin', component: AdminListComponent }
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
