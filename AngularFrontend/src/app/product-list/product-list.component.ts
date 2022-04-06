import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../model/product';
import { ProductlistService } from '../services/productlist.service';
import {MatCardModule} from '@angular/material/card';
import {ProductOrders} from "../model/product-orders.model";
import {ProductDetails} from "../model/product-details";
import {Subscription} from "rxjs";
import {error} from "@angular/compiler/src/util";
import {Order} from "../model/order.model";
import {CartService} from "../services/cart.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  public term: string
  public products!: Product[];
  // Cart service
  public prodList: any;

  constructor(router: Router, route: ActivatedRoute, private productsService: ProductlistService, private cartService: CartService) {
    this.term = route.snapshot.paramMap.get('term') || ""
  }

  ngOnInit(): void {
    this.productsService.getProducts().subscribe((products: Product[]) => {
      this.products = products
    }, (error: ErrorEvent) => {
    });

    this.prodList.forEach((x: Product)=>{
      Object.assign(x, {quantity: 1, total: x.price});
    })
  }

  addToCart(prod: Product){
    this.cartService.addToCart(prod);
  }


}
