import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DriverReportPageComponent } from './driver-report-page.component';

describe('DriverReportPageComponent', () => {
  let component: DriverReportPageComponent;
  let fixture: ComponentFixture<DriverReportPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DriverReportPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DriverReportPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
