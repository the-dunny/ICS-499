import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from 'src/app/models/player/player.model';


const baseUrl = 'http://localhost:8082/player/all';
const deleteUrl = 'http://localhost:8082/player/delete';


@Injectable({
  providedIn: 'root'
})
export class AdminService {
  deleted: boolean;

  

  constructor(private http: HttpClient) { }

  getPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(baseUrl);
  }

   deletePlayer(playerId: any): Promise<any>{

   return  this.http.get(deleteUrl, {
      params: {
        id: playerId.toString()
      },
      observe: 'response'
    })
      .toPromise()
      .then(() => {
      });

  }

}
