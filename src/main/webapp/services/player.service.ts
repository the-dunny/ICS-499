import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
//import { Player } from '../model/player.model';

const baseUrl = 'http://localhost:8081/player/highscore';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: HttpClient) {
  }

  getHighScores(): Observable<any> {
    return this.http.get(baseUrl);
  }


}