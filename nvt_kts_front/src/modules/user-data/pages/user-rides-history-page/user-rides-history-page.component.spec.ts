import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserRidesHistoryPageComponent } from './user-rides-history-page.component';

describe('UserRidesHistoryPageComponent', () => {
  let component: UserRidesHistoryPageComponent;
  let fixture: ComponentFixture<UserRidesHistoryPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserRidesHistoryPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserRidesHistoryPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
