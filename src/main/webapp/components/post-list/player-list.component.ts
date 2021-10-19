import { Component, OnInit } from '@angular/core';
import { Player } from '../../model/player.model';
import { PlayerService } from '../../services/player.service';
import { ActivatedRoute } from 'angular-route';

@Component({
  // selector: 'webapp-',
  templateUrl: './best-score.html'
})
export class PlayerListComponent implements OnInit {

  players?: Player[];
  selected?: Player;
  currentIndex: number = -1;


  constructor(private playerService: PlayerService, private route: ActivatedRoute) {
  }


  ngOnInit(): void {
    this.playerService.getHighScores()
      .subscribe(
        data => {
          this.players = data;
        },
        error => {
          console.error(error);
        });
  }

 
}