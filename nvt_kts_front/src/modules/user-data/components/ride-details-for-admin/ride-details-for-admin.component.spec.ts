import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RideDetailsForAdminComponent } from './ride-details-for-admin.component';

describe('RideDetailsForAdminComponent', () => {
  let component: RideDetailsForAdminComponent;
  let fixture: ComponentFixture<RideDetailsForAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RideDetailsForAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RideDetailsForAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
