import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RideDetailsForUserComponent } from './ride-details-for-user.component';

describe('RideDetailsForUserComponent', () => {
  let component: RideDetailsForUserComponent;
  let fixture: ComponentFixture<RideDetailsForUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RideDetailsForUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RideDetailsForUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
