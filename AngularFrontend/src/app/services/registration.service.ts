import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) { }

  register(data: any) {
    return this.http.post("http://localhost:8080/api/v1/account/register", data);
  }

}
