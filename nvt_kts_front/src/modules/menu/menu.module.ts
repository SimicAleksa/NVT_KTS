import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './component/menu/menu.component';
import { DriverRidesComponent } from './component/driver-rides/driver-rides.component';
import { RouterModule } from '@angular/router';
import { routes } from './menu-routing';
import { RegisteredUsersRidesComponent } from './component/registered-users-rides/registered-users-rides.component';


@NgModule({
  declarations: [
    MenuComponent,
    DriverRidesComponent,
    RegisteredUsersRidesComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
  ],
  exports: [
    MenuComponent
  ]
})
export class MenuModule { }
