import { Component, OnInit } from '@angular/core';
import { Tutorial } from 'src/app/models/tutorial.model';
import { TutorialService } from 'src/app/services/tutorial.service';
import { MatTableDataSource } from "@angular/material/table";


@Component({
  selector: 'app-tutorials-list',
  templateUrl: './tutorials-list.component.html',
  styleUrls: ['./tutorials-list.component.css']
})
export class TutorialsListComponent implements OnInit {

  tutorials?: Tutorial[];
  currentTutorial: Tutorial = {};
  currentIndex = -1;


  // columns we will show on the table
  public displayedColumns = ['Username', 'Score'];
  //the source where we will get the data
  public dataSource = new MatTableDataSource<Tutorial>();


  constructor(private tutorialService: TutorialService) { }

  ngOnInit(): void {
    this.retrieveTutorials();
  }

  retrieveTutorials(): void {
    this.tutorialService.getAll()
      .subscribe((res) => {

        this.dataSource.data = res;
      }),
      (error: any) => {
        console.error(error);
      };
  }

}