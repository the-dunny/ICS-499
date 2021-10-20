import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TutorialsListComponent } from './components/tutorials-list/tutorials-list.component';



const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
//  { path: 'newGame', component: TutorialsListComponent },
  { path: 'bestScores', component: TutorialsListComponent }
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
