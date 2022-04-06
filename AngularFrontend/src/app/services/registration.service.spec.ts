import { TestBed } from '@angular/core/testing';

import { RegistrationService } from '../../../../../../Current/buyandsellcgi/AngularFrontend/src/app/services/registration.service';

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
