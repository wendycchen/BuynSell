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
  ufname:any;
  ulname:any;
  
  constructor(private authServ: AuthenticationService) { }

  ngOnInit(): void {
    console.log(" i am inside profile component ----- ")
    console.log(this.authServ.data);
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
