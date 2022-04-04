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
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        throw new Error("Method not implemented.");
    }
} 