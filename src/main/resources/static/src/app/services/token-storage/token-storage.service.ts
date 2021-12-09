import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

const TOKEN = "jwt-token"
const USER = "curr-user"


@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }
  // User related properties
  private loginStatus = new BehaviorSubject<boolean>(this.checkLoginStatus());
  private displayName = new BehaviorSubject<string>(localStorage.getItem('curr-user')!);

  signOut() {
    this.loginStatus.next(false);
    location.pathname = "login";
  }

  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN)
    window.localStorage.setItem(TOKEN, token)
  }

  public getCurrentToken(): string | null {
    return window.localStorage.getItem(TOKEN);
  }

  public saveSessionUser(user: any): void {
    window.localStorage.removeItem(USER)
    window.localStorage.setItem(USER, user)
    //for displaying username and correct log button
    this.displayName.next(localStorage.getItem('curr-user')!);
    this.loginStatus.next(true);

  }

  public getBearerToken(): string {
    return `Bearer ` + this.getCurrentToken();
  }

  public getUser(): any {
    const user = window.localStorage.getItem(USER)
    if (user) { return JSON.parse(user) }
    return {
    };
  }

  get isLoggesIn() {
    return this.loginStatus.asObservable();
  }

  get currentDisplayName() {
    return this.displayName.asObservable();
  }

  checkLoginStatus(): boolean {

    let anyone = window.localStorage.getItem(TOKEN);
    if (anyone === null) {
      return false;
    }
    return true;
  }


}
