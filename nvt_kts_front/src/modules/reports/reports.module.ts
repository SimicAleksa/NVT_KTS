import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DriverChartComponent } from './components/driver-chart/driver-chart.component';
import { RouterModule } from '@angular/router';
import { routes } from './reports-routing';
import { RegisteredUserChartComponent } from './components/registered-user-chart/registered-user-chart.component';
import { DriverReportPageComponent } from './pages/driver-report-page/driver-report-page.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RegisteredUserPageComponent } from './pages/registered-user-page/registered-user-page.component';
import { AdminReportPageComponent } from './pages/admin-report-page/admin-report-page.component';
import { AdminChartComponent } from './components/admin-chart/admin-chart.component';
import { PopUpModule } from "../pop-up/pop-up.module";
import { SidebarsModule } from '../sidebars/sidebars.module';



@NgModule({
    declarations: [
        DriverChartComponent,
        RegisteredUserChartComponent,
        DriverReportPageComponent,
        RegisteredUserPageComponent,
        AdminReportPageComponent,
        AdminChartComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        RouterModule.forChild(routes),
        PopUpModule,
        SidebarsModule,
    ]
})
export class ReportsModule { }
