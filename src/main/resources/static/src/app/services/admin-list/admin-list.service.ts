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
 

  

  constructor(private http: HttpClient) { }

  getPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(baseUrl);
  }

   async deletePlayer(playerId: any): Promise<any>{

   await this.http.get(deleteUrl, {
       params: {
         id: playerId.toString()
       },
       observe: 'response'
     })
       .toPromise();

  }

}
