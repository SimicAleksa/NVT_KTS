import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUsersRidesComponent } from './registered-users-rides.component';

describe('RegisteredUsersRidesComponent', () => {
  let component: RegisteredUsersRidesComponent;
  let fixture: ComponentFixture<RegisteredUsersRidesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisteredUsersRidesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisteredUsersRidesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
