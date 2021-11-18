import {Component} from '@angular/core';
import { AuthServiceService } from './services/auth-service/auth-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoggedIn(): boolean{
    let x = sessionStorage.getItem('authenticatedUser');
    if(x === null) return true
    return false
  }

  title = 'LineGame';
}
