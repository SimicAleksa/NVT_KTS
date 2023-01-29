import { Routes } from '@angular/router';
import { RoleGuard } from '../app/guards/role.guard';
import { DriverRidesComponent } from './component/driver-rides/driver-rides.component';
import { RegisteredUsersRidesComponent } from './component/registered-users-rides/registered-users-rides.component';
export const routes: Routes = [
  {
    path:"driver-rides",
    component : DriverRidesComponent,
    canActivate: [RoleGuard],
    data:{expectedRoles:"DRIVER"}
  },
    {
      path:"registered-user-rides",
      component : RegisteredUsersRidesComponent,
      canActivate: [RoleGuard],
      data:{expectedRoles:"USER"}
    },
];



