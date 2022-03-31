import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  loginStatus:any;

  constructor(private http : HttpClient) { }

  generateToken(request: any){
    return this.http.post("http://localhost:9006/api/v1/account/authenticate",request,{responseType:'text' as 'json'});
  }

  setBearerToken(token : string){
    localStorage.setItem('Bearer',token);
  }

  getToken(){
    return localStorage.getItem('Bearer');
  }

  setLoginStatus(status:number){
    this.loginStatus = status;
  }

  isUserAuthenticated(token: any): Promise<boolean> {
    return this.http.post(`http://localhost:9006/api/v1/account/authenticate`,{},
    {
      headers: new HttpHeaders().set("Authorization", `Bearer ${token}`)
    })
    .pipe(
      map((res:any) => {
        return res["isAuthenticated"];
      })
    ).toPromise();
  }

}
