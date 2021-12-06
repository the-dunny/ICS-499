import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
const baseURL = 'http://localhost:8082/player/add';
const checkUnURL = 'http://localhost:8082/player/checkUn';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }

  addUser(un: string, pwd: string) {
    return this.http.post(baseURL, {"username": un, "password": pwd}, httpOptions);
  }


 async checkUserName(un: string): Promise<Object> {

   return (await this.http.get(checkUnURL, {params: { "username": un }}).toPromise());

  }

}
