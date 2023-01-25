import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaypalPageComponent } from './paypal-page.component';

describe('PaypalPageComponent', () => {
  let component: PaypalPageComponent;
  let fixture: ComponentFixture<PaypalPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaypalPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaypalPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
