import { LiveAnnouncer } from '@angular/cdk/a11y';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';

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

  public _liveAnnouncer: LiveAnnouncer;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;




  constructor(private playerervice: PlayerService) { }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

  }


  ngOnInit(): void {
    this.retrieveplayer();
  }

  retrieveplayer(): void {
    this.playerervice.getHighScores()
      .subscribe((res) => {

        this.dataSource = new MatTableDataSource(res)
        this.ngAfterViewInit();
      }),
      (error: any) => {
        console.error(error);
      };
  }

  /** Announce the change in sort state for assistive technology. */
  announceSortChange(sortState: Sort) {
    // This example uses English messages. If your application supports
    // multiple language, you would internationalize these strings.
    // Furthermore, you can customize the message to add additional
    // details about the values being sorted.
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }
  





}