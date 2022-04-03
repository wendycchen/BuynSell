import { Component, OnInit } from '@angular/core';
import { RoutingService } from '../services/routing.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private routerService:RoutingService) { }

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
  enterCart(){
    this.routerService.openCart();
  }
}
