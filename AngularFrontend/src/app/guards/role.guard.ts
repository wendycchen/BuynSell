import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { AuthenticationService } from "../services/authentication.service";
import { RoutingService } from "../services/routing.service";

// @Injectable({
//     providedIn:'root'
// })
// export class RoleGuard implements CanActivate {
//     constructor(private router: RoutingService, private authServ: AuthenticationService) {
//         // const 
//     }
// }