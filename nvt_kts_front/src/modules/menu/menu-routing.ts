import { Routes } from '@angular/router';
import { DriverRidesComponent } from './component/driver-rides/driver-rides.component';
import { RegisteredUsersRidesComponent } from './component/registered-users-rides/registered-users-rides.component';
export const routes: Routes = [
  {
    path:"driver-rides",
    component : DriverRidesComponent},
    {
      path:"registered-user-rides",
      component : RegisteredUsersRidesComponent},
    

];



