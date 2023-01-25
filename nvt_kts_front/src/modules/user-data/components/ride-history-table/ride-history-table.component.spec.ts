import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RideHistoryTableComponent } from './ride-history-table.component';

describe('RideHistoryTableComponent', () => {
  let component: RideHistoryTableComponent;
  let fixture: ComponentFixture<RideHistoryTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RideHistoryTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RideHistoryTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
