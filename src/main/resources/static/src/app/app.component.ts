import {Component} from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'LineGame';
  unloggedIn: boolean = true;

  setLoggedIn():void{
    this.unloggedIn = false;
  }
  
}
