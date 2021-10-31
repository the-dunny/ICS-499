import { Component, OnInit } from '@angular/core';

import { MatTableDataSource } from "@angular/material/table";
import { Player } from '../../models/player-list/player.model';
import { PlayerService } from '../../services/player-list/player.service';


@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent implements OnInit {


  // columns we will show on the table
  public displayedColumns = ['Rank', 'Username', 'Score'];
  //the source where we will get the data
  public dataSource = new MatTableDataSource<Player>();


  constructor(private playerervice: PlayerService) { }

  ngOnInit(): void {
    this.retrieveplayer();
  }

  retrieveplayer(): void {
    this.playerervice.getHighScores()
      .subscribe((res) => {

        this.dataSource = new MatTableDataSource(res)
      }),
      (error: any) => {
        console.error(error);
      };
  }

}