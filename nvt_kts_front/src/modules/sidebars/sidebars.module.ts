import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DriverRidesComponent } from '../user-data/components/driver-rides/driver-rides.component';
import { RegisteredUsersRidesComponent } from './components/registered-users-rides/registered-users-rides.component';



@NgModule({
  declarations: [
    DriverRidesComponent,
    RegisteredUsersRidesComponent,
  ],
  imports: [
    CommonModule
  ],
  exports: [
    DriverRidesComponent,
    RegisteredUsersRidesComponent,
  ]
})
export class SidebarsModule { }
