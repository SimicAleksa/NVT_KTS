import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchRoutesPageComponent } from './search-routes-page.component';

describe('SearchRoutesPageComponent', () => {
  let component: SearchRoutesPageComponent;
  let fixture: ComponentFixture<SearchRoutesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchRoutesPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchRoutesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
