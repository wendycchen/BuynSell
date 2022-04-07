import { inject, TestBed } from '@angular/core/testing';
import { User } from '../model/user';
import { RegistrationService } from './registration.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';


const user:User[] = [
  {
    firstName:"testing",
    lastName:"stillTesting",
    email:"testing@email.com",
    password:"test",
    username:"testinguser"
  }
];

describe('RegistrationService', () => {
  let service: RegistrationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegistrationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  
});
