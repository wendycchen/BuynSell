import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { AuthenticationService } from "../services/authentication.service";
import { RoutingService } from "../services/routing.service";

@Injectable({
    providedIn:'root'
})
export class RoleGuard implements CanActivate {
    constructor(private router: RoutingService, private authServ: AuthenticationService) {
        // const 
    }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
       const expectedRole = route.data["expectedRole"];
       const currentUser = this.authServ.getLogUser();
       console.log("GETTING TOKEN--- > " + this.authServ.getToken());
       console.log("CURRENT ====== " + currentUser.role);
    
       if(currentUser.role === expectedRole) {
           return true;
       }
       this.router.openHome();
       return false;
    }
} 