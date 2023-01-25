import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { routes } from './paypal-routing';
import { FormsModule } from '@angular/forms';
import { PaypalComponent } from './components/paypal/paypal.component';
import { PaypalPageComponent } from './pages/paypal-page/paypal-page.component';


@NgModule({
  declarations: [
    PaypalComponent,
    PaypalPageComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
  ]
})
export class PaypalModule { }
