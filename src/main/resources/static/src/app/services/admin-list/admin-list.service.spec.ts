import { TestBed } from '@angular/core/testing';

import { AdminService } from './admin-list.service';

describe('AdminListService', () => {
  let service: AdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
