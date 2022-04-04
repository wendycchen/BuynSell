import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { RoutingService } from '../services/routing.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private routerService:RoutingService, public authService: AuthenticationService) { }

  ngOnInit(): void {
  }

  enterSell() {
    this.routerService.openSell();
  }

  enterProduct() {
    this.routerService.openProduct();
  }
  
  enterLogin() {
    this.routerService.openLogin();
  }

  enterHome() {
    this.routerService.openHome();
  }

  enterAdmin() {
    this.routerService.openAdmin();
  }

  enterProfile() {
    this.routerService.openProfile();
  }

  enterSetting() {
    this.routerService.openSetting();
  }

  logOut() {
    this.authService.setLoginStatus(0);
    this.authService.logOut();
    // console.log("---" );
    // console.log(this.authService.getToken());
    // Does setting it to empty means logging out?
    // this.authService.setBearerToken(''); 
    this.routerService.openHome();
  }

}
