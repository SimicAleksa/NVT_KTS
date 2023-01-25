import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProbaPageComponent } from './proba-page.component';

describe('ProbaPageComponent', () => {
  let component: ProbaPageComponent;
  let fixture: ComponentFixture<ProbaPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProbaPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProbaPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
