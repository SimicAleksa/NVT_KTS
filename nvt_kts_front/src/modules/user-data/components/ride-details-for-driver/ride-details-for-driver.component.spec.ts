import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RideDetailsForDriverComponent } from './ride-details-for-driver.component';

describe('RideDetailsForDriverComponent', () => {
  let component: RideDetailsForDriverComponent;
  let fixture: ComponentFixture<RideDetailsForDriverComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RideDetailsForDriverComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RideDetailsForDriverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
