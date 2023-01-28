import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavouriteRoutesListComponent } from './favourite-routes-list.component';

describe('FavouriteRoutesListComponent', () => {
  let component: FavouriteRoutesListComponent;
  let fixture: ComponentFixture<FavouriteRoutesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FavouriteRoutesListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FavouriteRoutesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
