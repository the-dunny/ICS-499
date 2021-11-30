import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Token } from 'src/app/models/token/token.model';
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
      this.router.navigateByUrl(this.authenticationService.getRedirectUrl());
      
    }
  }

  onSubmit(): void{
    const { username, password } = this.form;
    this.authenticationService.authenticationService(username, password).subscribe(
      data => {
        console.log(data.token)
        this.tokenStorage.saveToken(data.token)
        this.tokenStorage.saveSessionUser(username)
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.router.navigateByUrl(this.authenticationService.getRedirectUrl());
       
      },
      err =>{
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    )
 
  }

}