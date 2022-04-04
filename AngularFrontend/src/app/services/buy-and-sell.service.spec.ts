import { TestBed } from '@angular/core/testing';

import { BuyAndSellService } from './buy-and-sell.service';

describe('BuyAndSellService', () => {
  let service: BuyAndSellService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BuyAndSellService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
