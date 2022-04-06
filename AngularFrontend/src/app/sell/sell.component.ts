import { Component, OnInit } from '@angular/core';
import { AnyForUntypedForms, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { RoutingService } from '../services/routing.service';
import {ProductlistService} from "../services/productlist.service";
import {Product} from "../model/product";
import {User} from "../model/user";
import { AngularFireStorage } from '@angular/fire/compat/storage';
import { finalize, Observable } from 'rxjs';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.css'],
})
export class SellComponent implements OnInit {
  pForm:any;
  url: string='';
  warningMessage: string = '';
  missingFieldsMessage: string = '';
  count = 1;
  product : Product = new Product();
  user: any;
  username: string = '';
  imageTime:any;
  imageUrl!: Observable<string>;
  imageUploaded:any;
  image:any;

  constructor(
    private productService : ProductlistService,
    private routerService: RoutingService,
    private authService: AuthenticationService,
    private firebase: AngularFireStorage,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.pForm = new FormGroup({
      pname: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
      category: new FormControl('', Validators.required),
      brand: new FormControl('', [Validators.required, Validators.minLength(2)]),
      condition: new FormControl('', Validators.required),
      price: new FormControl('', [Validators.required, Validators.pattern("^[0-9]+(\.[0-9][0-9]?)?")]),
      desc: new FormControl('', Validators.required),
      image: new FormControl('')
    })

    // this.image = this.formBuilder.group({
    //   file: new FormControl('')
    // })

    this.user = this.authService.getLogUser();
    this.username = this.user.username;
  };

  verifyProduct(event :any){
    console.log("this.pForm --->", this.pForm)


    
    let productData = {
      "pname":this.pForm.get('pname').value,
      "brand":this.pForm.get('brand').value,
      "category":this.pForm.get('category').value,
      "condition":this.pForm.get('condition').value,
      "price":this.pForm.get('price').value,
      "desc":this.pForm.get('desc').value,
      // "image":this.pForm.get('image').value
      "image":this.image
    }
    /*console.log(this.pForm.valid);
    console.log(this.pForm.get('condition').value);
    console.log(this.pForm.get('inputTitle').value);
    console.log(this.pForm.get('brand').value);
    console.log(this.pForm.get('category').value);
    console.log(this.pForm.get('price').value);
    console.log(this.pForm.get('desc').value);
    console.log(this.urls);
    */
    if(this.pForm.valid){
      // this.product = new Product();
      this.product.prodId = this.getRandomInt();
      this.product.price = productData.price;
      this.product.pname = productData.pname;
      this.product.condition = productData.condition;
      this.product.brand = productData.brand;
      this.product.desc = productData.desc;
      this.product.category = productData.category;
      // this.product.imageUrl = productData.image;
      this.product.postedBy = this.username;
      this.product.image = productData.image;
      //this.product.image = productData.image;
      this.product.date = new Date();


      console.log("this.pFormmmmmmm ---------------> " + this.pForm.value);
      this.save();
    } else {
      this.missingFieldsMessage = "Please fill in all the fields.";
    }
  }
  getRandomInt() {
    return Math.random()*9999;
  }

  save(){
    console.log("THIS IS MY PRODUCT "  + this.product);
    this.productService.addProduct(this.product).subscribe(data => console.log(data),
      error => console.log(error));
    this.routerService.openProduct();
  }



  cancelPost(){
    document.getElementById("modal-btn")?.click();
  }

  returnToHome(){
    this.routerService.openHome();
  }

  onFileSubmit(imageUploaded:any, event:any) {
    console.log("this.inFileSubmit evet--->", event)
    event.stopPropagation();
    event.preventDefault();
    this.imageTime = Date.now();
    const file: File = imageUploaded.files[0];
    console.log(file);
    const folder = `BuynSell/${this.imageTime}`;
    console.log(folder);
    const ref = this.firebase.ref(folder);
    const task = this.firebase.upload(`BuynSell/${this.imageTime}`, file);
    console.log(task);
    task.snapshotChanges()
        .pipe(finalize(() => {
          this.imageUrl = ref.getDownloadURL();
          this.imageUrl.subscribe(link => {
            if(link) {
              this.url = link;
              this.pForm.value.image = this.url;
              this.image = this.url
              // console.log("input pform ----> " + this.pForm.value.inputImage.toString());
              console.log("on file submit image URL: ");
              console.log(this.url);
              console.log(this.pForm);
              // this.productService.addProduct(this.pForm.value).subscribe(data => console.log(data), error => console.log(error));
            }
          });
        })
      )
      .subscribe((url: any) => {
        if(url) {
          console.log(url);
        }
      });
    
  }

}
