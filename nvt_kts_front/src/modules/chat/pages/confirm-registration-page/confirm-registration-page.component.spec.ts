import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmRegistrationPageComponent } from './confirm-registration-page.component';

describe('ConfirmRegistrationPageComponent', () => {
  let component: ConfirmRegistrationPageComponent;
  let fixture: ComponentFixture<ConfirmRegistrationPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmRegistrationPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfirmRegistrationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
