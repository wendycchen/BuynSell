import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product';
import { AuthenticationService } from '../services/authentication.service';
import { ProductlistService } from '../services/productlist.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user:any;
  ufname: string = '';
  ulname:string = '';
  email:string = '';
  username:string='';
  products: Product[] = [];
  productsByMe: Array<Product> = [];
  
  constructor(private authServ: AuthenticationService, private prodService: ProductlistService) { }

  ngOnInit(): void {
    console.log(this.authServ.getLogUser());
    this.user = this.authServ.getLogUser();
    this.ufname = this.user.firstName.charAt(0).toUpperCase() + this.user.firstName.slice(1);
    this.ulname = this.user.lastName.charAt(0).toUpperCase() + this.user.lastName.slice(1);
    this.email = this.user.email;
    this.username = this.user.username;

    this.prodService.getProducts().subscribe((products: Product[]) => {
      this.products = products;
      for(var i = 0; i < this.products.length; i++) {
        if(this.products[i].postedBy === this.username){
          this.productsByMe.push(this.products[i]);
        }
      }
    }, (error: ErrorEvent) => {
      console.log(error);
    })



  }

  deleteProduct(prodId: number) {
    this.prodService.deleteProduct(prodId).subscribe((data: any) => {
      this.productsByMe = [];
      this.ngOnInit();
    }, (err:any) => {
      console.log(err);
    })
  }


}
