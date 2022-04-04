import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../model/product';
import { ProductlistService } from '../services/productlist.service';
import {MatCardModule} from '@angular/material/card';
import {ProductOrders} from "../model/product-orders.model";
import {ProductDetails} from "../model/product-details";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  public term : string
  public products!: Product[];
  // Cart service
  productOrders: ProductDetails[] = [];
  //selectedProductOrder: ProductDetails;
  // private shoppingCartOrders: ProductOrders;
  // sub: Subscription;
  productSelected: boolean = false;

  constructor(router : Router, route : ActivatedRoute, private productsService : ProductlistService) {
      this.term = route.snapshot.paramMap.get('term') || ""
  }

  ngOnInit(): void {
      this.productsService.getProducts().subscribe((products : Product[]) => {
          this.products = products
      }, (error: ErrorEvent) => {
      });
      this.productOrders=[];
      // this.loadOrders();
  }
/*
  loadOrders(){
    this.sub = this.productsService.OrdersChanged.subscribe(()=>{
      this.shoppingCartOrders = this.productsService.ProductOrders;
    });
  }*/
/*
  addToCart(order: ProductDetails){
    this.productsService.SelectedProductOrder=order;
    this.selectedProductOrder = this.productsService.SelectedProductOrder;
    this.productSelected = true;
  }

  removeFromCart(productOrder: ProductDetails){
    let i = this.getProductIndex(productOrder.product);
    if(i > -1){
      this.shoppingCartOrders.productOrders.splice(
        this.getProductIndex(productOrder.product), 1
      );
    }
    this.productsService.ProductOrders = this.shoppingCartOrders;
    this.shoppingCartOrders = this.productsService.ProductOrders;
    this.productSelected = false;
  }
*/
}
