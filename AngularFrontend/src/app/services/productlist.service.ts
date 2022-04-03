import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../model/product';
import {Order} from "../model/order.model";
import {ProductOrders} from "../model/product-orders.model";
@Injectable({

  providedIn: 'root'
})

export class ProductlistService {

  public order = Order;
  private productOrder = ProductOrders;
  private orderSubject = new Subject();
  private productOrderSubject = new Subject();
  private totalSubject = new Subject();
  private total?: number;

  constructor(private http: HttpClient) { }

  getProducts() : Observable<Product[]>{
    return this.http.get<Product[]>("http://localhost:8076/productMicro/products");
  }

  getProduct(prodId : string): Observable<Product>{
    return this.http.get<Product>("http://localhost:8076/productMicro/products/"+prodId);
  }

  saveOrder(order: ProductOrders){
    return this.http.post("http://localhost:9004/api/orders/order/newOrder", order);
  }

}

