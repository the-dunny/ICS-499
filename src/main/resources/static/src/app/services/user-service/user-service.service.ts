import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Player } from 'src/app/models/player/player.model';
const baseURL = 'http://localhost:8082/player/add';
@Injectable({
  providedIn: 'root'
})
export class UserServiceService { 

  constructor(private http: HttpClient) { }

  addUser(un : string , pwd : string) : Observable<Player> {
    return this.http.post<Player>(baseURL, {username : un, pwd});
    
  }
}
