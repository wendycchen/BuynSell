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

  openSetting() {
    this.routerService.navigate(['profile/setting']);
  }

  openProduct() {
    this.routerService.navigate(['product']);
  }

  openAdmin() {
    this.routerService.navigate(['admin']);


  }
  openCart(){
    this.routerService.navigate(['cart']);
  }
  
  openCheckout(){
    this.routerService.navigate(['checkout'])
  }
}
