import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DriverChartComponent } from './components/driver-chart/driver-chart.component';
import { RouterModule } from '@angular/router';
import { routes } from './reports-routing';
import { RegisteredUserChartComponent } from './components/registered-user-chart/registered-user-chart.component';
import { DriverReportPageComponent } from './pages/driver-report-page/driver-report-page.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RegisteredUserPageComponent } from './pages/registered-user-page/registered-user-page.component';



@NgModule({
  declarations: [
    DriverChartComponent,
    RegisteredUserChartComponent,
    DriverReportPageComponent,
    RegisteredUserPageComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)
  ]
})
export class ReportsModule { }
