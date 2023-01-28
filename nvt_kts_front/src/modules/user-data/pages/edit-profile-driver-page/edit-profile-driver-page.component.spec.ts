import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProfileDriverPageComponent } from './edit-profile-driver-page.component';

describe('EditProfileDriverPageComponent', () => {
  let component: EditProfileDriverPageComponent;
  let fixture: ComponentFixture<EditProfileDriverPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditProfileDriverPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditProfileDriverPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
