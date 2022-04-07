import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { RoutingService } from '../services/routing.service';
import {RegistrationService} from "../services/registration.service";


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  rForm:any;
  message:string = '';
  userEmail:string='';
  constructor(private routerService:RoutingService, private authService: AuthenticationService, private registrationService : RegistrationService) {
  }

  ngOnInit(): void {
    this.rForm = new FormGroup({
      inputUsername: new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(50)]),
      inputPassword: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(50)]),
      inputConfirmPassword: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(50)]),
      inputEmail: new FormControl('', [Validators.required, Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,4}$")]),
      inputFirstName: new FormControl('', Validators.required),
      inputLastName: new FormControl('', Validators.required),
    })
  }

  verifyRegister(fForm:any) {

    let userData = {
      "firstName": this.rForm.get('inputFirstName')?.value,
      "lastName":this.rForm.get('inputLastName')?.value,
      "email": this.rForm.get('inputEmail')?.value,
      "password":this.rForm.get('inputPassword')?.value,
      "username":this.rForm.get('inputUsername')?.value,

    }
    console.log(this.rForm.get('inputPassword').value);
    console.log(this.rForm.get('inputConfirmPassword').value);
    if(this.rForm.valid){
      if((this.rForm.get('inputPassword').value)==(this.rForm.get('inputConfirmPassword').value)) {
        this.userEmail = this.rForm.get('inputEmail')?.value;
        document.getElementById("modal-btn")?.click();
        // this.routerService.openHome();
        this.registrationService.register(userData).subscribe((res: any) => {
          console.log("res ->", res)
        })
      } else {
        this.message = "Passwords do not match.";
      }


    }

  }


  enterLogin() {
    this.routerService.openLogin();

  }




}
