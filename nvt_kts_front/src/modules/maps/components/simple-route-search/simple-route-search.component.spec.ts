import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimpleRouteSearchComponent } from './simple-route-search.component';

describe('SimpleRouteSearchComponent', () => {
  let component: SimpleRouteSearchComponent;
  let fixture: ComponentFixture<SimpleRouteSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SimpleRouteSearchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SimpleRouteSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
