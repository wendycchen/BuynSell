import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../model/product';
import {Order} from "../model/order.model";
import {ProductOrders} from "../model/product-orders.model";
import {ProductDetails} from "../model/product-details";
@Injectable({

  providedIn: 'root'
})

export class ProductlistService {


  private productOrder!: ProductDetails;
  private orders: ProductOrders = new ProductOrders();
  private productOrderSubject = new Subject();
  private ordersSubject = new Subject();
  private totalSubject = new Subject();
  public total?: number;

  ProductOrderChange = this.productOrderSubject.asObservable();
  OrdersChanged = this.ordersSubject.asObservable();
  TotalChanged = this.totalSubject.asObservable();

  constructor(private http: HttpClient) { }

  getProducts() : Observable<Product[]>{
    return this.http.get<Product[]>("http://localhost:9001/productMicro/products");
  }

  getProduct(prodId : string): Observable<Product>{
    return this.http.get<Product>("http://localhost:9001/productMicro/products/"+prodId);
  }

  saveOrder(order: ProductOrders){
    return this.http.post("http://localhost:9001/api/orders/order/newOrder", order);
  }

}

