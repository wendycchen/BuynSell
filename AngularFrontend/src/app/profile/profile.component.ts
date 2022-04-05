import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user:any;
  ufname: string = '';
  ulname:string = '';
  email:string = '';
  
  constructor(private authServ: AuthenticationService) { }

  ngOnInit(): void {
    console.log(" i am inside profile component ----- ")
    console.log(this.authServ.getLogUser());
    this.user = this.authServ.getLogUser();
    this.ufname = this.user.firstName.charAt(0).toUpperCase() + this.user.firstName.slice(1);
    this.ulname = this.user.lastName.charAt(0).toUpperCase() + this.user.lastName.slice(1);
    this.email = this.user.email;
    // this.userServ.getUser().subscribe(
    //   (data:any) => {
    //     this.user = data;
    //     this.ufname = this.user[0].ufname;
    //     this.ulname = this.user[0].ulname;
    //     // this.email = this.user[0].email;
    //     // this.blockList = this.user[0].blocked;
    //     // this.update();
    //   }, (err:any) => {
    //     console.log(err);
    //   });
  }

}
