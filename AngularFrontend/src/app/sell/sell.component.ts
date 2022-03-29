import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { CategorylistService } from '../services/categorylist.service';
import { RoutingService } from '../services/routing.service';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.css'],
})
export class SellComponent implements OnInit {
  pForm:any;
  categories: any = [];
  urls = new Array<string>();
  warningMessage: string = '';
  missingFieldsMessage: string = '';
  count = 1;
  maxImageCount = 4;

  constructor(
    private catService: CategorylistService,
    private routerService: RoutingService,
    private authService: AuthenticationService
  ) {}

  ngOnInit(): void {
    this.catService.getCategories().subscribe(
      (data: any) => {
        this.categories = data;
      },
      (err: any) => {
        console.log(err);
      }
    )
    this.pForm = new FormGroup({
      inputTitle: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
      inputCategory: new FormControl('', Validators.required),
      inputBrand: new FormControl('', [Validators.required, Validators.minLength(3)]),
      inputCondition: new FormControl('', Validators.required),
      inputPrice: new FormControl('', [Validators.required, Validators.pattern("^[0-9]+(\.[0-9][0-9]?)?")]),
      inputDescription: new FormControl('', Validators.required),
      inputImage: new FormControl('')
    })
  };

  preview(event: any) {
    let files = event.target.files;
    if (files) {
      for (let file of files) {
        let reader = new FileReader();

        reader.onload = (event: any) => {
          this.urls.push(event.target.result);
          this.count += 1;
          
        };
        reader.readAsDataURL(file);
      }
    }
  }

  verifyProduct(pForm:any){
    let productData = {
      "pTitle":this.pForm.get('inputTitle').value,
      "pBrand":this.pForm.get('inputBrand').value,
      "pCategory":this.pForm.get('inputBrand').value,
      "pCondition":this.pForm.get('inputCondition').value,
      "pPrice":this.pForm.get('inputPrice').value,
      "pDescription":this.pForm.get('inputDescription').value,
      "pImage":this.urls
    }
    console.log(this.pForm.valid);
    console.log(this.pForm.get('inputCondition').value);
    console.log(this.pForm.get('inputTitle').value);
    console.log(this.pForm.get('inputBrand').value);
    console.log(this.pForm.get('inputCategory').value);
    console.log(this.pForm.get('inputPrice').value);
    console.log(this.pForm.get('inputDescription').value);
    console.log(this.urls);

    if(this.pForm.valid){
      // Redirect to see product page or home page?
    } else {
      this.missingFieldsMessage = "Please fill in all the fields.";
    }


  }


  cancelPost(){
    document.getElementById("modal-btn")?.click();
  }
  
  returnToHome(){
    this.routerService.openHome();
  }

}
