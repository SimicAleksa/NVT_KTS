import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDrivesHistoryPageComponent } from './user-drives-history-page.component';

describe('UserDrivesHistoryPageComponent', () => {
  let component: UserDrivesHistoryPageComponent;
  let fixture: ComponentFixture<UserDrivesHistoryPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserDrivesHistoryPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserDrivesHistoryPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
