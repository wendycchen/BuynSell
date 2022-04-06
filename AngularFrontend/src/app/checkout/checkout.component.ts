import { Component, OnInit } from '@angular/core';
import {RoutingService} from "../services/routing.service";
import {max} from "rxjs";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {


  constructor(private router : RoutingService) { }

  ngOnInit(): void {
  }

  submitCheckout(){
    console.log("submitted checkout")
    this.router.openSubmitOrder();
  }


}
