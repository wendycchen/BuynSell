import { Component, OnInit, ɵɵsetComponentScope } from '@angular/core';
import { AnyForUntypedForms, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { RoutingService } from '../services/routing.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-setting',
  templateUrl: './setting.component.html',
  styleUrls: ['./setting.component.css']
})
export class SettingComponent implements OnInit {
  
  uForm:any;
  user:any;
  uid:any;
  lastName:any;
  firstName:string='';
  email:string='';
  username:string='';
  token:string='';
  role:string='';
  ufnameDisplay: string='';
  ulnameDisplay: string='';

  pForm:any;
  warningMessage:string='';
  
  constructor(private authServ:AuthenticationService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {

    this.user = this.authServ.getLogUser();
    console.log(this.user);
    this.firstName = this.user.firstName;
    this.lastName = this.user.lastName;
    this.email = this.user.email;
    this.ufnameDisplay = this.firstName.charAt(0).toUpperCase() + this.firstName.slice(1);
    this.ulnameDisplay = this.lastName.charAt(0).toUpperCase() + this.lastName.slice(1);

    this.uForm = new FormGroup({
      ufname: new FormControl('', Validators.required),
      ulname: new FormControl('', Validators.required),
      email: new FormControl('',Validators.required)
    })

    this.update();

    

      this.pForm = new FormGroup({
        newPass: new FormControl('', Validators.required),
        newConfirmPass: new FormControl('', Validators.required)
      })  
  }

  update() {
    console.log(this.firstName);
    this.uForm.get('ufname').setValue(this.firstName);
    this.uForm.get('ulname').setValue(this.lastName);
    this.uForm.get('email').setValue(this.email);
  }

  updateProfile(uForm:any){
    this.user.firstName = this.uForm.get('ufname').value;
    this.user.lastName = this.uForm.get('ulname').value;
    this.user.email = this.uForm.get('email').value;

    console.log("inside update profile------");
    console.log(this.user);


    this.authServ.logUser(this.user);
    this.ngOnInit();
  }

  updatePassword(pForm:any){
    console.log(this.pForm.get('newPass').value);
    console.log(this.pForm.get('newConfirmPass').value);
    const newPass = this.pForm.get('newPass').value;
    const newCPass = this.pForm.get('newConfirmPass').value;
    if(newPass === (newCPass)){
      //allow to change pass ----- send to backend
    } else {
      this.warningMessage = "Please check your passwords again.";
    }
    
  }

  // unblockUser(id:number){
  //   this.blockList = this.blockList.filter((item:any) => item.id !== id);
  //   this.uForm.value.blocked = this.blockList;
  //   this.userServ.updateUser(this.uForm.value).subscribe((data:any) => {
  //     if(data != null) {
  //       console.log("UPDATED OK");

  //     } else {
  //       console.log("NO UPDATE");
  //     }
  //   })
  // }

}
