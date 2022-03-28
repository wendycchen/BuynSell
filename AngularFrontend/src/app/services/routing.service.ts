import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RoutingService {

  constructor(private routerService:Router) { }

  openLogin() {
    this.routerService.navigate(['login']);
  }

  openHome() {
    this.routerService.navigate(['']);
  }

  openRegister() {
    this.routerService.navigate(['register']);
  }

  openProfile(){
    this.routerService.navigate(['profile']);
  }

  openChat() {
    this.routerService.navigate(['chat']);
  }

  openSell() {
    this.routerService.navigate(['sell']);
  }
}
