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

    this.authService.authenticateUser(userData).subscribe((res: any) => {
      console.log("res ->", res)
      this.token = res.token;
      // this.authService.isUserAuthenticated(this.token);

      this.authService.setBearerToken(this.token);
      // this.authService.data = res;
      this.authService.logUser(res);
      this.routerService.openHome();

    })
  }



}
