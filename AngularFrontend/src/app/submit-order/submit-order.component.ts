import { Component, OnInit } from '@angular/core';
import {RoutingService} from "../services/routing.service";
import {CartService} from "../services/cart.service";
import {ProductlistService} from "../services/productlist.service";
import {Product} from "../model/product";

@Component({
  selector: 'app-submit-order',
  templateUrl: './submit-order.component.html',
  styleUrls: ['./submit-order.component.css']
})
export class SubmitOrderComponent implements OnInit {
  orderNum!: number;
  constructor(private routerService: RoutingService, private cartService:CartService,
              private productService: ProductlistService) { }

  ngOnInit(): void {
  }

  autoGenOrderNum(): number{
    let min = Math.ceil(100000);
    let max = Math.ceil(100000000);
    this.orderNum = Math.floor(Math.random() * (max - min + 1) + min);
    console.log(this.orderNum)
    return this.orderNum;
  }

  openHome(){
    this.routerService.openHome();
  }


}
