import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { AuthenticationService  } from './services/authentication.service';
import { Observable, tap } from 'rxjs';
import { Injectable } from '@angular/core';
import { RoutingService } from './services/routing.service';


@Injectable({
    providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor {

    constructor(public authServ: AuthenticationService, private routerServ: RoutingService) {}

    intercept(req: HttpRequest<any>, next:HttpHandler): Observable<HttpEvent<any>> {
        const clonedRequest = req.clone({
            // headers: req.headers.set('X-Requested-With', 'XMLHttpRequest'),
            // withCredentials: true
            setHeaders: {
                            Authorization: `Bearer ${this.authServ.getToken()}`
                        }
        })
        ;
        // return next.handle(clonedRequest);
        return next.handle(clonedRequest).pipe( tap(() => {},
        (err: any) => {
            if(err instanceof HttpErrorResponse) {
                if(err.status !== 401) {
                    return;
                }
                this.routerServ.openLogin();
            }
        }
        ));
    }
}