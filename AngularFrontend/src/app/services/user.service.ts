import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }

  getUser(): Observable<any> {
    return this.httpClient.get<User>('http://localhost:3000/user');
  }

  updateUser(user:any): Observable<any> {
    return this.httpClient.put(`http://localhost:3000/user/${user.id}`, user)
  }


}
