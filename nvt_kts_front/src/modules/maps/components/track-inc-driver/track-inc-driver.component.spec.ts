import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackIncDriverComponent } from './track-inc-driver.component';

describe('TrackIncDriverComponent', () => {
  let component: TrackIncDriverComponent;
  let fixture: ComponentFixture<TrackIncDriverComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrackIncDriverComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrackIncDriverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
