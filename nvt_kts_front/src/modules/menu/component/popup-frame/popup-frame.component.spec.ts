import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupFrameComponent } from './popup-frame.component';

describe('PopupFrameComponent', () => {
  let component: PopupFrameComponent;
  let fixture: ComponentFixture<PopupFrameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupFrameComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopupFrameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
