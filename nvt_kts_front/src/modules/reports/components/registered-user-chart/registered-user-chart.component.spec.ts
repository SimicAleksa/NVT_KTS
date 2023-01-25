import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUserChartComponent } from './registered-user-chart.component';

describe('RegisteredUserChartComponent', () => {
  let component: RegisteredUserChartComponent;
  let fixture: ComponentFixture<RegisteredUserChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisteredUserChartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisteredUserChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
