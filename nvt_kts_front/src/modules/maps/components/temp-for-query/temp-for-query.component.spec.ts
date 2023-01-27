import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TempForQueryComponent } from './temp-for-query.component';

describe('TempForQueryComponent', () => {
  let component: TempForQueryComponent;
  let fixture: ComponentFixture<TempForQueryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TempForQueryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TempForQueryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
