import { Routes } from '@angular/router';
import { RoleGuard } from '../app/guards/role.guard';
import { DriverRidesComponent } from '../user-data/components/driver-rides/driver-rides.component';
import { RegisteredUsersRidesComponent } from '../sidebars/components/registered-users-rides/registered-users-rides.component';
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



