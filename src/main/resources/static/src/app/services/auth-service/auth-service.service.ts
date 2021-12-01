import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { Role } from 'src/app/models/role/role.modle';
import { Token } from 'src/app/models/token/token.model';

const baseUrl = 'http://localhost:8082/authenticate';
const roleUrl = 'http://localhost:8082/user/roles';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private router: Router;
  public username: string;
  public password: string;
  public role: Role = new Role();
  // USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'curr-user'
  USER_ROLE_SESSION_ATTRIBUTE_NAME = 'userRole';
  // store the URL so we can redirect after logging in
  redirectUrl: string = "";
  admin: boolean = false;



  constructor(private http: HttpClient) { }

  authenticationService(un: string, pw: string): Observable<Token> {
    return this.http.post<Token>(baseUrl, { "username": un, "password": pw }, httpOptions)


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
    this.router.navigate(['/login']);
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    console.log("isUserLoggedIn: " + user)
    if (user === null) {
      return false;
    }
    return true;
  }

  async isUserAdmin(): Promise<boolean> {
    await this.setRole();
    await this.getRole().then(res => {

      if (res === "ROLE_ADMIN") {
        this.admin = true;
      }
    });

    // return this.admin;
    return this.admin;


  }

  async getRole(): Promise<string> {
    return sessionStorage.getItem(this.USER_ROLE_SESSION_ATTRIBUTE_NAME)!;

  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    if (user === null) return '';
    return user;
  }
  setRedirectUrl(url: string) {

    this.redirectUrl = url;
  }
  getRedirectUrl(): string {
    return this.redirectUrl;
  }

  async setRole(): Promise<void> {
    const response = (await this.http.get<Role>(roleUrl).toPromise()).roles;
    sessionStorage.setItem(this.USER_ROLE_SESSION_ATTRIBUTE_NAME, response);
  }
}