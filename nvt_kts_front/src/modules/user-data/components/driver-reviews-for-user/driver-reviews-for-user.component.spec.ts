import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DriverReviewsForUserComponent } from './driver-reviews-for-user.component';

describe('DriverReviewsForUserComponent', () => {
  let component: DriverReviewsForUserComponent;
  let fixture: ComponentFixture<DriverReviewsForUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DriverReviewsForUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DriverReviewsForUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
