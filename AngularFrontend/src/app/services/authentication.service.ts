import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, JsonpClientBackend} from "@angular/common/http";
import { map } from 'rxjs/operators';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  loginStatus:any = 0;
  userData: any;

  constructor(private http : HttpClient) { }


  authenticateUser(data: any) {
    return this.http.post("http://localhost:8080/api/v1/account/login", data);
  }

  generateToken(request: any){
    return this.http.post("http://localhost:8080/authenticate",request,{responseType:'text' as 'json'});
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

  logOut() {
    localStorage.removeItem('Bearer');
    localStorage.removeItem('UserInfo');

  }

  logUser(val: any) {
    localStorage.setItem("UserInfo", JSON.stringify(val));
    
  } 

  getLogUser(): any{
    return JSON.parse(localStorage.getItem("UserInfo") as string);
  }

  isUserAuthenticated(): boolean {
    return !!localStorage.getItem("UserInfo");
  }



}
