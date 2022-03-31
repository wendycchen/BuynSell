import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root'
})

export class ProductlistService {

  constructor(private http: HttpClient) { }

  getProducts() : Observable<Product[]>{
    return this.http.get<Product[]>("http://localhost:8076/productMicro/products");
  }

  getProduct(prodId : string): Observable<Product>{
    return this.http.get<Product>("http://localhost:8076/productMicro/products/"+prodId);
  }
}

