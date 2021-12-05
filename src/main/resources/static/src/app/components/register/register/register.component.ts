import { Component, OnInit, Input, Inject } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service/auth-service.service';
import { UserServiceService } from 'src/app/services/user-service/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  form: any = {
    username: null,
    password: null,
    password2: null
  }

  errorMessage = '';
  invalidRegister = false;
  validRegister = false;
  repeat = false;
  constructor(
    public dialog: MatDialog,
    private router: Router,
    private userServiceService: UserServiceService
   ) { }

  ngOnInit() {

  }
  async handleRegistration() {
    const { username, password, password2 } = this.form;

    if (password != password2) {
      this.errorMessage = 'Password does not match.';
      this.invalidRegister = true;
      return;
    }

    if ((await this.userServiceService.checkUserName(username)).toString() === "true") {
      this.errorMessage = 'That Username already exists.';
      this.invalidRegister = true;
      return;
    }

    this.userServiceService.addUser(username, password).subscribe(data =>{
      console.log(data);
    });

    this.invalidRegister = false;
    this.validRegister = true;
    this.openDialog(username);
    this.router.navigate(['login']);

  }

  openDialog(uName: string) {
    let dialogRef = this.dialog.open(MessageComponent, {
      data: { name: uName}
    });

  }

}


@Component({
  selector: 'message-component',
  templateUrl: './message-component.html',
  template: 'passed in {{data}}  '
})
export class MessageComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: { name: string}) { }

}