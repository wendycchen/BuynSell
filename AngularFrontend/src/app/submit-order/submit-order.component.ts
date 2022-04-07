import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { ProductlistService } from '../services/productlist.service';
import {RoutingService} from "../services/routing.service";

import {Product} from "../model/product";

@Component({
  selector: 'app-submit-order',
  templateUrl: './submit-order.component.html',
  styleUrls: ['./submit-order.component.css']
})
export class SubmitOrderComponent implements OnInit {
  orderNum!: number;
  constructor(private routerService: RoutingService, private prodServ: ProductlistService, private cartServ: CartService) { }

  ngOnInit(): void {
    console.log("PLS SHOW ITEM LIST----->" + this.cartServ.itemList);
    for(var i = 0; i < this.cartServ.itemList.length; i++) {
      console.log(this.cartServ.itemList[i]);
      this.prodServ.deleteProduct(this.cartServ.itemList[i].prodId).subscribe((data:any) => {
        console.log(data);
      }, (err:any) => {
        console.log(err);
      })
    }
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
