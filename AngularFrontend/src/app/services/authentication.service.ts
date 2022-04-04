import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { map } from 'rxjs/operators';

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
  }

  get data(): any {
    console.log("I am inside authentication service get DAta");
    console.log(this.userData);

    return this.userData;
  }

  set data(val: any){
    console.log("inside set data in authentication service");
    this.userData = val;
    console.log(this.userData);
  }

  isUserAuthenticated(token: any) {
    return this.http.post(`http://localhost:8080/authenticate`,{},
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
