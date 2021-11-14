import {Component, OnInit} from '@angular/core';
import {NewGameService} from '../../services/new-game/new-game.service';
// import {drawLinePuzzle} from '../../../assets/js/drawLinePuzzle';

@Component({
  selector: 'app-new-game',
  templateUrl: './new-game.component.html',
  styleUrls: ['./new-game.component.css']
})
export class NewGameComponent implements OnInit {

  constructor(private newGameService: NewGameService) { }

  ngOnInit(): void {
    this.retrieveGame();
  }

  retrieveGame(): void {
    // drawLinePuzzle(6);
  }
}