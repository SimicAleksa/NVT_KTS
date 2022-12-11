import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDriverPageComponent } from './add-driver-page.component';

describe('AddDriverPageComponent', () => {
  let component: AddDriverPageComponent;
  let fixture: ComponentFixture<AddDriverPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDriverPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddDriverPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
