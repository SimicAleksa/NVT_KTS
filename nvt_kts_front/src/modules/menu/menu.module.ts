import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './component/menu/menu.component';
import { DriverRidesComponent } from '../user-data/components/driver-rides/driver-rides.component';
import { RouterModule } from '@angular/router';
import { routes } from './menu-routing';
import { RegisteredUsersRidesComponent } from '../sidebars/components/registered-users-rides/registered-users-rides.component';
import { PopUpModule } from "../pop-up/pop-up.module";
import { PopupFrameComponent } from './component/popup-frame/popup-frame.component';

@NgModule({
    declarations: [
        MenuComponent,
        PopupFrameComponent,
    ],
    exports: [
        MenuComponent,
    ],
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        PopUpModule,
    ]
})
export class MenuModule { }
