import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimpleRoutesSearchPageComponent } from './simple-routes-search-page.component';

describe('SimpleRoutesSearchPageComponent', () => {
  let component: SimpleRoutesSearchPageComponent;
  let fixture: ComponentFixture<SimpleRoutesSearchPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SimpleRoutesSearchPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SimpleRoutesSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
