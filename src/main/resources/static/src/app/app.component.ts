import {Component, OnInit} from '@angular/core';
import { TokenStorageService } from './services/token-storage/token-storage.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'LineGame';
  unloggedIn: string | null= localStorage.getItem('curr-user');
  username: string | null = localStorage.getItem('curr-user');

  constructor(private tokenStorage: TokenStorageService){}

  ngOnInit(): void {
    this.unloggedIn = localStorage.getItem('curr-user');
    this.username = localStorage.getItem('curr-user');
  }

  setLoggedIn():void{
    this.unloggedIn = localStorage.getItem('curr=user');
  }
  
  checkLoginStatus(){
    if(this.tokenStorage.getUser != null){
      this.unloggedIn = localStorage.getItem('curr-user');
      return false;
    }
    return true;
  }

  lO(){
    localStorage.clear();
    this.unloggedIn == null;
    this.username == null;
    location.reload()
  }

}
