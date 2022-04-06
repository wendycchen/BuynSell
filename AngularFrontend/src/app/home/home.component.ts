import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product';
import { ProductlistService } from '../services/productlist.service';
import { RoutingService } from '../services/routing.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products!: Product[];
  adminProducts: Array<Product> = [];
  message: any;
  constructor(private productsService: ProductlistService, private route:RoutingService) { }

  ngOnInit(): void {
    this.message = localStorage.getItem('Bearer');
    this.productsService.getProducts().subscribe((products: Product[]) => {
      this.products = products;
      for(var i = 0; i < this.products.length; i++) {
        if(this.products[i].postedBy === "ADMIN") {
          this.adminProducts.push(this.products[i]);
        }
      }
    }, (error: ErrorEvent) => {
    });

  }

  enterProducts() {
    this.route.openProduct();
  }

}
