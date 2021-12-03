import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service/auth-service.service';
import { UserServiceService } from 'src/app/services/user-service/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  @Input() username: string;
  @Input() password: string;
  @Input() password2: string;

  token?: string;
  errorMessage = 'Not matching Passwords';
  successMessage: string;
  invalidRegister = false;
  validRegister = false;
  constructor(
   private route: ActivatedRoute,
   private router: Router,
   private userServiceService: UserServiceService,
   private authenticationService: AuthServiceService) {   }
    
  ngOnInit() {
  }
  handleRegistration(){
    if(this.password != this.password2){
      this.invalidRegister = true;
      return;
    }
    this.userServiceService.addUser(this.username, this.password).subscribe((res => {

      console.log(res);


    }));
   
  //   this.authenticationService.authenticationService(this.username, this.password).subscribe((result)=> {
  //     this.invalidRegister = false;
  //     this.validRegister = true;
  //     this.successMessage = 'User Created, Login Successful.';
  //     this.router.navigate(['newGame']);
  //   }, () => {
  //     this.invalidRegister = true;
  //     this.validRegister = false;
  //   }); 
   }
}
