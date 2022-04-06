import { Component, OnInit } from '@angular/core';
import {RoutingService} from "../services/routing.service";

@Component({
  selector: 'app-submit-order',
  templateUrl: './submit-order.component.html',
  styleUrls: ['./submit-order.component.css']
})
export class SubmitOrderComponent implements OnInit {
  orderNum!: number;
  constructor(private routerService: RoutingService) { }

  ngOnInit(): void {
  }

  autoGenOrderNum(): number{
    let min = Math.ceil(100000);
    let max = Math.ceil(100000000);
    this.orderNum = Math.floor(Math.random() * (max - min + 1) + min);
    console.log(this.orderNum)
    return this.orderNum;
  }

  openHome(){
    this.routerService.openHome();
  }

}
