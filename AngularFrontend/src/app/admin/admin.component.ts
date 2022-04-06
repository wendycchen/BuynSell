import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product';
import { AuthenticationService } from '../services/authentication.service';
import { ProductlistService } from '../services/productlist.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  public products!: Product[];
  
  constructor(private productsService: ProductlistService) { }

  ngOnInit(): void {
    this.productsService.getProducts().subscribe((products: Product[]) => {
      this.products = products
      console.log(this.products);
    }, (error: ErrorEvent) => {
    });
  } 
  
  deleteProduct(productId: any){
    this.productsService.deleteProduct(productId).subscribe((data: any) => {
      console.log(data);
      this.ngOnInit();
    }, (err:any) => {
      console.log(err);
    })
  }

}
