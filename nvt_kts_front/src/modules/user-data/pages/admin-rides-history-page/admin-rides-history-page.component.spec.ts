import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRidesHistoryPageComponent } from './admin-rides-history-page.component';

describe('AdminRidesHistoryPageComponent', () => {
  let component: AdminRidesHistoryPageComponent;
  let fixture: ComponentFixture<AdminRidesHistoryPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminRidesHistoryPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminRidesHistoryPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
