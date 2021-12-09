import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LinePuzzle} from 'src/app/models/line-puzzle/line-puzlle.model';


const baseUrl = 'http://localhost:8082/game/';

@Injectable({
  providedIn: 'root'
})

export class NewGameService {
  private displayName = Math.random().toString(32).slice(2);

  constructor(private http: HttpClient) { }
  getNewGame(gridSize: number): Observable<LinePuzzle> {
    try {
      this.displayName = localStorage.getItem('curr-user')!.toString();
    } catch (error) {
      localStorage.setItem('curr-user', this.displayName);
    }
    return this.http.get<LinePuzzle>(baseUrl + this.displayName + "/newGame?gridSize=" + gridSize);

  }

  updateCurrentGame(key: number): Observable<LinePuzzle> {
    return this.http.get<LinePuzzle>(baseUrl + this.displayName + "/currentGame?keyPressed=" + key);
  }

  updateScore(score: number): Observable<LinePuzzle> {
    return this.http.get<LinePuzzle>(baseUrl + this.displayName + "/currentGame?keyPressed=" + score);
  }
}