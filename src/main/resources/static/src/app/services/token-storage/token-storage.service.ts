import { Injectable } from '@angular/core';

const TOKEN = "jwt-token"
const USER = "curr-user"

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut(){
    window.sessionStorage.clear
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN)
    window.sessionStorage.setItem(TOKEN, token)
  }

  public getCurrentToken(): string | null {
    return window.sessionStorage.getItem(TOKEN);
  }

  public saveSessionUser(user: any): void{
    window.sessionStorage.removeItem(USER)
    window.sessionStorage.setItem(USER, user)
  }

  public getUser(): any{
    const user = window.sessionStorage.getItem(USER)
    if(user){return JSON.parse(user)}
    return {
    };
  }

}
