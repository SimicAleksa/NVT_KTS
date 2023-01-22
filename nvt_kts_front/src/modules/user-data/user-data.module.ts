import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import { RouterModule } from '@angular/router';
import { routes } from './user-data-routing';
import { AddDriverPageComponent } from './pages/add-driver-page/add-driver-page.component';
import { AddCarComponent } from './components/add-car/add-car.component';
import { EditProfilePageComponent } from './pages/edit-profile-page/edit-profile-page.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { ApproveChangesPageComponent } from './pages/approve-changes-page/approve-changes-page.component';
import { ApproveChangesComponent } from './components/approve-changes/approve-changes.component';
import { FormsModule } from '@angular/forms';
import { UserDrivesHistoryPageComponent } from './pages/user-drives-history-page/user-drives-history-page.component';
import { RideHistoryTableComponent } from './components/ride-history-table/ride-history-table.component';
import { PopUpModule } from '../pop-up/pop-up.module'; 
import { DatePipe } from '@angular/common';



@NgModule({
  declarations: [
    RegistrationFormComponent,
    RegistrationPageComponent,
    AddDriverPageComponent,
    AddCarComponent,
    EditProfilePageComponent,
    EditProfileComponent,
    ApproveChangesPageComponent,
    ApproveChangesComponent,
    UserDrivesHistoryPageComponent,
    RideHistoryTableComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes),
    PopUpModule
  ],
  providers: [
    DatePipe
  ]
})
export class UserDataModule { }
