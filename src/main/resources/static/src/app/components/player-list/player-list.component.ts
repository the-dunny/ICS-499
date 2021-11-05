
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { Sort } from '@angular/material/sort';

import { MatTableDataSource } from "@angular/material/table";
import { Player } from '../../models/player-list/player.model';
import { PlayerService } from '../../services/player-list/player.service';


@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent implements OnInit, AfterViewInit {

  // columns we will show on the table
  public displayedColumns = ['Rank', 'Username', 'Score'];
  //the source where we will get the data
  public dataSource = new MatTableDataSource<Player>();

  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private playerervice: PlayerService) { }


  ngOnInit() {

    this.retrieveplayer();
  }

  retrieveplayer() {

    this.playerervice.getHighScores()
      .subscribe((res) => {
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.paginator = this.paginator;

      }),
      (error: any) => {
        console.error(error);
      };
  }

  ngAfterViewInit(): void {

  }

  public customSort = (event: any) => {
    console.log(event);
  }

  sortData(sort: Sort) {
    // Sort sorts the current list, but it wasnt updating it unless i reassigned.
    this.dataSource.data = this.dataSource.data.sort((a, b) => {

      const isAsc = sort.direction === 'asc';

      if (sort.active == "Score") {

        return this._compare(a.bestScore, b.bestScore, isAsc);
      }

      else if (sort.active == "Username") {

        return this._compare(a.userName, b.userName, isAsc);

      }
      else {
        return 1;
      }

    });
  }
  private _compare(a: string, b: string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
}