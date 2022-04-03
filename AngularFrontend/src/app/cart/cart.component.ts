import { Component, OnInit } from '@angular/core';
import {RoutingService} from "../services/routing.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartContent: any;

  constructor(private routerService: RoutingService) {
  }

  ngOnInit(): void {
  }

  addToCart(id: number, quantity: number): void {
    console.log({id, quantity});
    this.cartContent = this.cartContent;
  }

  openCheckout() {
    console.log('click');
    this.routerService.openCheckout();

  }
}
