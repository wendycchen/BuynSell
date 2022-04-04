import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';
import { RoutingService } from '../services/routing.service';


@Injectable({
  providedIn:'root'
})
export class CanActivateRouteGuard implements CanActivate {

  constructor(private router:RoutingService, private auth:AuthenticationService) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
      let token = this.auth.getToken();
      console.log("INSIDE CAN ACTIVATE GUARD -----> " + token);
      if(token) {
        return true;
      }
      this.router.openLogin();
      return false;
      // const res = this.auth.isUserAuthenticated(token);

      // return res
      // .then((res:any) => {
      //   if(!res) {
      //     this.rouObj.openLogin();
      //   }
      //   return res;
      // })
      // .catch((err) => {
      //   this.rouObj.openLogin();
      //   return false;
      // })
  }
}
