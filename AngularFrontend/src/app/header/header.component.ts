import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { RoutingService } from '../services/routing.service';
import {CartService} from "../services/cart.service";
import { ProductlistService } from '../services/productlist.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  cartItems: number = 0;
  catoList: any = [];
  constructor(private routerService:RoutingService, public authService: AuthenticationService, private cartService: CartService, private prodService: ProductlistService) { }

  ngOnInit(): void {
    
    this.cartService.getProducts().subscribe(res=>{
      this.cartItems = res.length;
    })
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
    this.authService.logOut();
    this.routerService.openHome();
  }

  enterCart(){
    this.routerService.openCart();
  }


}
