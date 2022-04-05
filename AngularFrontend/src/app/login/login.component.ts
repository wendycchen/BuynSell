import { Component, OnInit, ɵɵsetComponentScope } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { RoutingService } from '../services/routing.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
//TODO add functionality for register and forgot password, create tests

  token: any;
  errorMessage!: string;

  numbers:any;

  form = new FormGroup({
    inputEmail: new FormControl('', ),
    inputPassword: new FormControl('',)
    // inputEmail: new FormControl('', Validators.required),
    // inputPassword: new FormControl('', Validators.required)

  })

  constructor(private routerService:RoutingService, private authService: AuthenticationService) {
   }

  ngOnInit(): void {
  }

  verifyLogin() {
    let userData = {
      // "email": "testuser1@abc.com",
      // "password":"user"
      "email": this.form.get('inputEmail')?.value,
      "password":this.form.get('inputPassword')?.value
    }
    console.log(this.form.value);
    console.log(this.form.get('inputEmail')?.value);
    console.log(this.form.get('inputPassword')?.value);

    this.authService.authenticateUser(userData).subscribe((res: any) => {
      console.log("res ->", res)
      this.token = res.token;
      // this.authService.isUserAuthenticated(this.token);
      
      this.authService.setBearerToken(this.token);
      // this.authService.data = res;
      this.authService.logUser(res);
      this.routerService.openHome();

      // this.authService.setBearerToken(tokenValue);
      // this.routerService.openHome();
    })



    // this.token = this.authService.generateToken(userData);
    // console.log(this.token);
    // this.token.subscribe((tokenValue: any) => {

    //   this.authService.setBearerToken(tokenValue);
    //   // console.log("tokenValue----- " + tokenValue);
    //   // this.authService.setLoginStatus(1);
    //   this.routerService.openHome();

    // }, (error: { message: string; }) => {
    //   //TODO
    //    console.log(error.message);
    //    this.errorMessage = "Invalid Login"
    // })

    // this.authService.isUserAuthenticated(userData).subscribe((tokenValue: any) => {
    //   this.routerService.openHome();
    // }, (err: {message: string;}) => {
    //   console.log(err.message);
    //   this.errorMessage = "invalid";
    // })
    

  }



}
