import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Player } from 'src/app/models/player/player.model';
const baseURL = 'http://localhost:8082';
@Injectable({
  providedIn: 'root'
})
export class UserServiceService { 
  private user : string;
  private password : string;

  constructor(private http: HttpClient) { }

  addUser(user : Player) : Observable<Player> {
    return this.http.post<Player>(baseURL, user);
  }
}
