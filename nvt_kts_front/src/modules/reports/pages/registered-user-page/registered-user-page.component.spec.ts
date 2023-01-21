import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUserPageComponent } from './registered-user-page.component';

describe('RegisteredUserPageComponent', () => {
  let component: RegisteredUserPageComponent;
  let fixture: ComponentFixture<RegisteredUserPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisteredUserPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisteredUserPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
