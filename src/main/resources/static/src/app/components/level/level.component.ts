import { Component, OnInit } from '@angular/core';
import { Level } from '../../models/level.model';
import { LevelService } from '../../services/level.service';
import { makeGrid } from '../../../assets/js/makeGrid.js';
import { ViewEncapsulation } from '@angular/core';


@Component({
  selector: 'app-level',
  templateUrl: './level.component.html',
  styleUrls: ['./level.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LevelComponent implements OnInit {

  // grid = new MakeGrid;
 

  level?: Level[];
  currentLevel: Level = {};
  currentIndex = -1;




  constructor(private levelservice: LevelService) { }


  ngOnInit(): void {
    /**  makeGrid() is JS function, file in loc. in assets/js/makeGrid.js, 
this is where you add J.S. must update scripts[]in angular.json with correct path. **/
    makeGrid();
  }


}