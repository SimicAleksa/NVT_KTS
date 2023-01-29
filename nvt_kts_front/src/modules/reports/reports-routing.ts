import { Routes } from '@angular/router';
import { RoleGuard } from '../app/guards/role.guard';
import { DriverChartComponent } from './components/driver-chart/driver-chart.component';
import { RegisteredUserChartComponent } from './components/registered-user-chart/registered-user-chart.component';
import { AdminReportPageComponent } from './pages/admin-report-page/admin-report-page.component';
import { DriverReportPageComponent } from './pages/driver-report-page/driver-report-page.component';
import { RegisteredUserPageComponent } from './pages/registered-user-page/registered-user-page.component';
export const routes: Routes = [
  {
    path:"driverChart",
    component : DriverReportPageComponent,
    canActivate: [RoleGuard],
    data:{expectedRoles:"DRIVER"}
  },
  {
    path:"registeredUserChart",
    component : RegisteredUserPageComponent,
    canActivate: [RoleGuard],
    data:{expectedRoles:"USER"}
  },
  {
    path:"adminChart",
    component : AdminReportPageComponent,
    canActivate: [RoleGuard],
    data:{expectedRoles:"ADMIN"}
  },
    

];



