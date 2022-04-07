import { Component, OnInit } from '@angular/core';
import {RoutingService} from "../services/routing.service";
import {Product} from "../model/product";
import {CartService} from "../services/cart.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  public product: any = [];
  public totalCost: number = 0;

  constructor(private routerService: RoutingService, private cartService: CartService) {
  }

  ngOnInit(): void {
    this.cartService.getProducts().subscribe(res=>{
      this.product = res;
      this.totalCost = this.cartService.getTotalPrice();
    })
  }
  deleteItem(prod: any){
    this.cartService.removeFromCart(prod);
    console.log("Removed")
  }

  removeAllItems(){
    this.cartService.emptyCart();
  }

  openCheckout() {
    console.log('click');
    this.routerService.openCheckout();
  }

  enterProduct() {
    this.routerService.openProduct();
  }

}
