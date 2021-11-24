import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service/auth-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  username: string;
  password: string;
  password2: string;
  token?: string;
  errorMessage = 'Not matching Passwords';
  successMessage: string;
  invalidRegister = false;
  validRegister = false;
  constructor(
   private route: ActivatedRoute,
   private router: Router,
   private authenticationService: AuthServiceService) {   }

  ngOnInit() {
  }
  handleRegistration(){
    if(this.password != this.password2){
      this.invalidRegister = true;
      return;
    }
    this.authenticationService.authenticationService(this.username, this.password).subscribe((result)=> {
      this.invalidRegister = false;
      this.validRegister = true;
      this.successMessage = 'Login Successful.';
      this.router.navigate(['bestScores']);
    }, () => {
      this.invalidRegister = true;
      this.validRegister = false;
    }); 
  }
}
