import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

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

}
