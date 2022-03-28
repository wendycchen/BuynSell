import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { CategorylistService } from '../services/categorylist.service';
import { RoutingService } from '../services/routing.service';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.css'],
})
export class SellComponent implements OnInit {
  categories: any;

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
    );
  }
}
