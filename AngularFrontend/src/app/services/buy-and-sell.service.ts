import { Injectable } from '@angular/core';
import {Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Order} from "../model/order.model";

@Injectable({
  providedIn: 'root'
})
export class BuyAndSellService {
  /* Dummy class -- ref
  private productsUrl = "/productMicro"; //Temp fix the links after
  private ordersUrl = "/api/orders" // fix link

  public Order = new Order();
  private orderSubject = new Subject();
  private productOrderSubject = new Subject();
  private totalSubject = new Subject();
  private total?: number;

  constructor(private http: HttpClient) { }

  getAllProducts(){
    return this.http.get(this.productsUrl);
  }

  saveOrder(){
    return this.http.post(this.ordersUrl, Order.e);
  }
*/
}
