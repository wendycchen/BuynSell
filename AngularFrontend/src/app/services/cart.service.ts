import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Product} from "../model/product";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  public itemList: any = [];
  public productList = new BehaviorSubject<any>([]);
  constructor() { }

  getProducts(){
    return this.productList.asObservable();
  }

  setProducts(product: any){
    this.itemList.push(...product);
    this.itemList.next(product);
  }

  addToCart(product: Product){
    this.itemList.push(product);
    this.productList.next(this.itemList);
    this.getTotalPrice();
    console.log(this.itemList);
  }

  getTotalPrice() : number{
    let tot = 0;
    this.itemList.map((x:Product)=> {
      tot += x.price;
    })
    return tot;
  }

  removeFromCart(product: Product){
    this.itemList.map((x:Product, i:Product)=>{
      if(product.prodId===x.prodId){
        this.itemList.splice(i, 1);
      }
    })
  }

  emptyCart(){
    this.itemList = [];
    this.productList.next(this.itemList);
  }
}
