import { Component, ElementRef, OnInit, ViewChild, Inject } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { Sort } from '@angular/material/sort';
import { MatTableDataSource } from "@angular/material/table";
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Player } from 'src/app/models/player/player.model';
import { AdminService } from "../../services/admin-list/admin-list.service";




@Component({
  selector: 'app-admin-list',
  templateUrl: './admin-list.component.html',
  styleUrls: ['./admin-list.component.css'],
})
export class AdminListComponent implements OnInit {



  // columns we will show on the table
  public displayedColumns = [ 'Action', 'PlayerID', 'Username', 'Active', 'Role'];
  //the source where we will get the data
  public dataSource = new MatTableDataSource<Player>();

  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();

  @ViewChild(MatPaginator) paginator: MatPaginator;



  @ViewChild('input') input: ElementRef;


  constructor(public dialog: MatDialog, private adminservice: AdminService, private router: Router) { }


  ngOnInit() {
    this.retrievePlayers();
  }
  retrievePlayers() {

    this.adminservice.getPlayers()
      .subscribe((res) => {
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.paginator = this.paginator;
      })
      ,
      (error: any) => {
        console.error(error);
      };
  }


  sortData(sort: Sort) {
    // Sort sorts the current list, but it wasnt updating it unless i reassigned.
    this.dataSource.data = this.dataSource.data.sort((a, b) => {

      const isAsc = sort.direction === 'asc';

      if (sort.active == "PlayerID") {

        return this._compare(a.playerID!, b.playerID!, isAsc);
      }

      else if (sort.active == "Username") {

        return this._compare(a.userName!, b.userName!, isAsc);

      }

      else if (sort.active == "Active") {

        return this._compare(a.active!.toString(), b.active!.toString(), isAsc);

      }
      else if (sort.active == "Role") {

        return this._compare(a.roles, b.roles, isAsc);

      }
      else {
        return 1;
      }

    });
  }
  private _compare(a: string, b: string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  openDialog(id: any, uName: string) {


    let dialogRef = this.dialog.open(DialogComponent, {
      data: { name: uName, plID: id }
    });


    dialogRef.afterClosed().subscribe(async result => {
      this.dialog.closeAll();
      if (result) {
        await this.adminservice.deletePlayer(id);
        // save current route first
        const currentRoute = this.router.url;

        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate([currentRoute]); // navigate to same route

        });
      }
    });
  }
}


@Component({
  selector: 'dialog-component',
  templateUrl: './dialog-component.html',
  template: 'passed in {{data}}  '
})
export class DialogComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: { name: string, plID: any }) { }

}



