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

  form = new FormGroup({
    inputEmail: new FormControl('', Validators.required),
    inputPassword: new FormControl('', Validators.required)

  })

  constructor(private routerService:RoutingService, private authService: AuthenticationService) {
   }

  ngOnInit(): void {
  }

  verifyLogin() {
    let uesrData = {
      "email": this.form.get('inputEmail')?.value,
      "password":this.form.get('inputPassword')?.value
    }
    console.log(this.form.value);
    console.log(this.form.get('inputEmail')?.value);
    console.log(this.form.get('inputPassword')?.value);

  }



}
