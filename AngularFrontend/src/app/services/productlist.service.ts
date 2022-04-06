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
  public orders: ProductOrders = new ProductOrders();

  private productOrderSubject = new Subject();
  private ordersSubject = new Subject();
  private totalSubject = new Subject();

  public total!: number;

  ProductOrderChange = this.productOrderSubject.asObservable();
  OrdersChanged = this.ordersSubject.asObservable();
  TotalChanged = this.totalSubject.asObservable();

  private allProductsUrl: string;
  private getProductsByIdUrl: string;
  private addProductsUrl: string;
  private deleteProductsUrl: string;
  private newOrderUrl: string;
  private allOrdersUrl: string;
  private orderByEmailUrl: string;
  private orderByOrderNumberUrl: string;


  constructor(private http: HttpClient) {
    this.allProductsUrl = "http://localhost:8080/productMicro/products";
    this.getProductsByIdUrl = "http://localhost:9001/productMicro/products";
    this.addProductsUrl = "http://localhost:8080/productMicro/products";
    this.deleteProductsUrl = "http://localhost:9001/productMicro/products/";
    this.newOrderUrl = "http://localhost:9004/api/orders/newOrder";
    this.allOrdersUrl = "http://localhost:9004/api/orders/order";
    this.orderByEmailUrl = "http://localhost:9004/api/orders/FindByEmail/";
    this.orderByOrderNumberUrl = "http://localhost:9004/api/orderNumber/";
  }

  getProducts() : Observable<Product[]>{
    return this.http.get<Product[]>(this.allProductsUrl);
  }

  getProduct(prodId : string): Observable<Product>{
    return this.http.get<Product>(this.getProductsByIdUrl+prodId);
  }

  addProduct(product: Product){
    return this.http.post(this.addProductsUrl, product);
  }

  deleteProduct(productId: number){
    return this.http.delete(this.deleteProductsUrl+productId);
  }

  addOrder(order: Order){
    return this.http.post(this.newOrderUrl, order);
  }

  getAllOrders(){
    return this.http.get(this.allOrdersUrl);
  }

  getOrderByEmail(email: String){
    return this.http.get(this.orderByEmailUrl+email);
  }

  getOrderByOrderNumber(orderNum: number){
    return this.http.get(this.orderByOrderNumberUrl+orderNum)
  }

}

