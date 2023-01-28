import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavouriteRoutesPageComponent } from './favourite-routes-page.component';

describe('FavouriteRoutesPageComponent', () => {
  let component: FavouriteRoutesPageComponent;
  let fixture: ComponentFixture<FavouriteRoutesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FavouriteRoutesPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FavouriteRoutesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
