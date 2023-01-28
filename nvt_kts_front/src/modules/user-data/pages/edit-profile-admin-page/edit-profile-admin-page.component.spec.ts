import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProfileAdminPageComponent } from './edit-profile-admin-page.component';

describe('EditProfileAdminPageComponent', () => {
  let component: EditProfileAdminPageComponent;
  let fixture: ComponentFixture<EditProfileAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditProfileAdminPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditProfileAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
