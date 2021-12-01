import { Injectable } from '@angular/core';

const TOKEN = "jwt-token"
const USER = "curr-user"

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut(){
    window.localStorage.clear
  }

  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN)
    window.localStorage.setItem(TOKEN, token)
  }

  public getCurrentToken(): string | null {
    return window.localStorage.getItem(TOKEN);
  }

  public saveSessionUser(user: any): void{
    window.localStorage.removeItem(USER)
    window.localStorage.setItem(USER, user)
  }

  public getBearerToken(): string{
    return `Bearer ` + this.getCurrentToken();
  }

  public getUser(): any{
    const user = window.localStorage.getItem(USER)
    if(user){return JSON.parse(user)}
    return {
    };
  }

}
