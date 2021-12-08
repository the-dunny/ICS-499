import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ScoreListComponent } from './components/score-list/score-list.component';
import { NewGameComponent } from './components/new-game/new-game.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { AdminListComponent } from './components/admin-list/admin-list.component';
import { RegisterComponent } from './components/register/register/register.component';
import { AboutComponent } from './components/about/about.component';


const routes: Routes = [
  { path: '', redirectTo: 'about', pathMatch: 'full' },
  { path: 'newGame', component: NewGameComponent },
  { canActivate: [AuthGuard], path: 'bestScores', component: ScoreListComponent },
  {canActivate: [AuthGuard], path: 'admin-list', component: AdminListComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'about', component: AboutComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
