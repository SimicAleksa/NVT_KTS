import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DriverRidesHistoryPageComponent } from './driver-rides-history-page.component';

describe('DriverRidesHistoryPageComponent', () => {
  let component: DriverRidesHistoryPageComponent;
  let fixture: ComponentFixture<DriverRidesHistoryPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DriverRidesHistoryPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DriverRidesHistoryPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
