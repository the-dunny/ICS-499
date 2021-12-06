import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenStorageService } from './services/token-storage/token-storage.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'LineGame';
  LoginStatus$ : Observable<boolean>;
  UserName$ : Observable<string>;

  constructor( private router: Router, private tokenStorage: TokenStorageService){}

  ngOnInit(): void {
    this.LoginStatus$ = this.tokenStorage.isLoggesIn;
    this.UserName$ = this.tokenStorage.currentDisplayName;
  }

  lO(){
    localStorage.clear();
    this.tokenStorage.signOut();
  }

}
