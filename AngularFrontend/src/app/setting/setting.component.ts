import { Component, OnInit } from '@angular/core';
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
  ufname:any;
  ulname:string='';
  email:string='';
  blockList:any;

  pForm:any;
  warningMessage:string='';
  
  constructor(private userServ: UserService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.userServ.getUser().subscribe(
      (data:any) => {
        this.user = data;
        this.uid = this.user[0].id;
        this.ufname = this.user[0].ufname;
        this.ulname = this.user[0].ulname;
        this.email = this.user[0].email;
        this.blockList = this.user[0].blocked;
        this.update();
      }, (err:any) => {
        console.log(err);
      });
      this.uForm = new FormGroup({
        ufname: new FormControl('', Validators.required),
        ulname: new FormControl('', Validators.required),
        email: new FormControl('',Validators.required)
      })

      this.pForm = new FormGroup({
        newPass: new FormControl('', Validators.required),
        newConfirmPass: new FormControl('', Validators.required)
      })  
  }

  update() {
    this.uForm = this.formBuilder.group({
      id:this.uid,
      ufname: this.ufname,
      ulname: this.ulname,
      email:this.email,
      blocked:this.blockList
    });
    console.log("inside update() ----> " + this.blockList);
    
  }

  updateProfile(uForm:any){
    console.log(this.uForm);
    console.log(this.uForm.valid);
    this.userServ.updateUser(this.uForm.value).subscribe((data:any) => {
      if(data != null) {
        console.log("UPDATED OK");
        this.ngOnInit();
      } else {
        console.log("NO UPDATE");
      }
    })
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

  unblockUser(id:number){
    this.blockList = this.blockList.filter((item:any) => item.id !== id);
    this.uForm.value.blocked = this.blockList;
    this.userServ.updateUser(this.uForm.value).subscribe((data:any) => {
      if(data != null) {
        console.log("UPDATED OK");

      } else {
        console.log("NO UPDATE");
      }
    })
  }

}
