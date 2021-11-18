import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators'
const baseUrl = 'http://localhost:8082/basic_auth';


@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  public username: string;
  public password: string;
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

  constructor(private http: HttpClient) { }

  authenticationService(un: string, pw: string){
    return this.http.get(baseUrl, {headers: {authorization: this.createBasicAuthToken(un, pw)}}).pipe(map((res) => {
      this.username = un;
      this.password = pw;
      this.registerSuccessfulLogin(this.username, this.password);
    }));
  }

  createBasicAuthToken(username: String, password: String) {
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  registerSuccessfulLogin(username: string, password: string) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username)
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    this.username = "";
    this.password = "";
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return false
    return true
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''
    return user
  }

}
