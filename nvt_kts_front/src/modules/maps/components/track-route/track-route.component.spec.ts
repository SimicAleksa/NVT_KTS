import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackRouteComponent } from './track-route.component';

describe('TrackRouteComponent', () => {
  let component: TrackRouteComponent;
  let fixture: ComponentFixture<TrackRouteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrackRouteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrackRouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
