import { Routes } from '@angular/router';
import { RoleGuard } from '../app/guards/role.guard';
import { PaypalPageComponent } from './pages/paypal-page/paypal-page.component';
export const routes: Routes = [
  {
    path:"paypal",
    component : PaypalPageComponent,
    canActivate: [RoleGuard],
    data:{expectedRoles:"USER"}
  },
  

];



