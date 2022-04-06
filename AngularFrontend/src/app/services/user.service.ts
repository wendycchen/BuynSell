import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }

  updateUser(pw:string): Observable<any> {
    return this.httpClient.put(`http://localhost:8080/api/v1/account/update`, pw);

  }


}
