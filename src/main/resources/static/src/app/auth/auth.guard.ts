import { Component, Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthServiceService } from '../services/auth-service/auth-service.service';
import { MatDialog } from '@angular/material/dialog';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  admin: boolean = false;

  constructor(public dialog: MatDialog, private authService: AuthServiceService, private router: Router) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Promise<true | UrlTree> {
    const url: string = state.url;
    return this.checkLogin(url);
  }

  async checkLogin(url: string): Promise<true | UrlTree> {
//check to see if they are validly loged in...
    if (this.authService.isUserLoggedIn()) {
      if (url === "/admin-list") {
        //check if they have admin role
        await this.authService.isUserAdmin().then(res => {
          this.admin = res.valueOf();
        }
        );
        if (!this.admin) {
          this.openDialog();
          return this.router.parseUrl('/');
        }
      }

      return true;

    }

    // Store the attempted URL for redirecting
    this.authService.setRedirectUrl(url);
    // Redirect to the login page
    return this.router.parseUrl('/login');
  }

  openDialog(): void {
    this.dialog.open(AuthDialogComponent
    );
  }
}

@Component({
  selector: 'auth-dialog',
  templateUrl: './auth-dialog.html',
})
export class AuthDialogComponent {

  constructor() { }

}

