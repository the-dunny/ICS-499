import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service/auth-service.service';
import { TokenStorageService } from 'src/app/services/token-storage/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {
    username: null,
    password: null
  }
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthServiceService,
    private tokenStorage: TokenStorageService) {   }

  ngOnInit(): void {
    if(this.tokenStorage.getCurrentToken()){
      this.isLoggedIn = true;
    }
  }

  onSubmit(): void{
    const { username, password } = this.form;
    this.authenticationService.authenticationService(username, password).subscribe(
      data => {
        console.log(data.toString)

        this.isLoginFailed = false;
        this.isLoggedIn = true;
      },
      err =>{
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    )
  }

}