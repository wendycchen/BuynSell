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
  public userProducts: Array<Product> = [];
  public adminProducts: Array<Product> = [];
  filteredCat:any;
  aList: any;
  uList: any;
  // Cart service
  public prodList: any;

  constructor(router: Router, route: ActivatedRoute, private productsService: ProductlistService, private cartService: CartService) {
    this.term = route.snapshot.paramMap.get('term') || ""
  }

  ngOnInit(): void {
    this.productsService.getProducts().subscribe((products: Product[]) => {
      this.products = products;
      console.log(this.products);
      for(var i = 0; i < this.products.length; i++) {
        if(this.products[i].postedBy === "ADMIN") {
          console.log("FOUND");
          this.adminProducts.push(this.products[i]);
        } else {
          this.userProducts.push(this.products[i]);
        }
      }
      console.log("-----------------------------------");
      console.log(this.adminProducts);
      console.log(this.userProducts);
    }, (error: ErrorEvent) => {
    });

    this.aList = this.adminProducts;
    this.uList = this.userProducts;
    this.prodList.forEach((x: Product)=>{
      Object.assign(x, {quantity: 1, total: x.price});
    })
  }

  addToCart(prod: Product){
    this.cartService.addToCart(prod);
  }

  filtered(event:any) {
    const filteredCat = (event.target.value);
    this.adminProducts = this.aList;
    this.userProducts = this.uList;
    if(filteredCat !== "All") {
      this.aList = this.adminProducts;
       this.adminProducts = this.adminProducts.filter(item => item.category === filteredCat);
      this.uList = this.userProducts;
      this.userProducts = this.userProducts.filter(item => item.category === filteredCat);
    }
  }


}
