import { Routes } from '@angular/router';
import { DriverChartComponent } from './components/driver-chart/driver-chart.component';
import { RegisteredUserChartComponent } from './components/registered-user-chart/registered-user-chart.component';
import { DriverReportPageComponent } from './pages/driver-report-page/driver-report-page.component';
export const routes: Routes = [
  {
    path:"driverChart",
    component : DriverReportPageComponent},
    {
      path:"registeredUserChart",
      component : RegisteredUserChartComponent},
    

];



