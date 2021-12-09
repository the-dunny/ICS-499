import {Component, OnInit} from '@angular/core';
import {LinePuzzle} from 'src/app/models/line-puzzle/line-puzlle.model';
import {Point} from 'src/app/models/point/point.model';
import {drawLinePuzzle} from 'src/assets/js/drawLinePuzzle';
import {NewGameService} from '../../services/new-game/new-game.service';

@Component({
  selector: 'app-new-game',
  templateUrl: './new-game.component.html',
  styleUrls: ['./new-game.component.css']
})

export class NewGameComponent implements OnInit {

  gridSize?: number = 3;
  linePuzzle?: LinePuzzle;
  travelVertexes?: Array<Array<Point>>;
  zoneVertexes?: Array<Array<Point>>;
  displayName?: String;

  constructor(private newGameService: NewGameService) { }
  
  ngOnInit(): void {
    this.initGame();
  }

  initGame(): void {
    this.displayName = localStorage.getItem('curr-user')?.toString();
    console.log(this.displayName);
    this.newGameService.getNewGame(this.gridSize!).forEach(element => {
        this.linePuzzle = element;
    }).then(() => {
      drawLinePuzzle(this.linePuzzle, this.newGameService);
    });
  }
}