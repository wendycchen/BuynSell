import { Component, OnInit } from '@angular/core';
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

  form = new FormGroup({
    inputEmail: new FormControl('', Validators.required),
    inputPassword: new FormControl('', Validators.required)

  })

  constructor(private routerService:RoutingService, private authService: AuthenticationService) {
   }

  ngOnInit(): void {
  }

  verifyLogin() {
    let userData = {
      "email": this.form.get('inputEmail')?.value,
      "password":this.form.get('inputPassword')?.value
    }
    console.log(this.form.value);
    console.log(this.form.get('inputEmail')?.value);
    console.log(this.form.get('inputPassword')?.value);

    this.token = this.authService.generateToken(userData);
    this.token.subscribe((tokenValue: any) => {
      this.authService.setBearerToken(tokenValue);
      this.routerService.openHome();

    }, (error: { message: string; }) => {
      //TODO
       console.log(error.message);
       this.errorMessage = "Invalid Login"
    })

  }



}
