import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveVehicleComponent } from './active-vehicle.component';

describe('ActiveVehicleComponent', () => {
  let component: ActiveVehicleComponent;
  let fixture: ComponentFixture<ActiveVehicleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActiveVehicleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActiveVehicleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
