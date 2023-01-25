import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
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
import { PopUpModule } from '../pop-up/pop-up.module'; 
import { DatePipe } from '@angular/common';
import { UserRidesHistoryPageComponent } from './pages/user-rides-history-page/user-rides-history-page.component';
import { RideHistoryTableComponent } from './components/ride-history-table/ride-history-table.component';
import { RideDetailsForUserComponent } from './components/ride-details-for-user/ride-details-for-user.component';
import { DriverReviewsForUserComponent } from './components/driver-reviews-for-user/driver-reviews-for-user.component';
import { StarRatingModule } from 'angular-star-rating';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BlockUserPageComponent } from './pages/block-user-page/block-user-page.component';
import { BlockUserComponent } from './components/block-user/block-user.component';

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
    UserRidesHistoryPageComponent,
    RideHistoryTableComponent,
    RideDetailsForUserComponent,
    DriverReviewsForUserComponent
    BlockUserPageComponent,
    BlockUserComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes),
    PopUpModule,
    StarRatingModule.forRoot(),
    ReactiveFormsModule,
    RouterModule.forChild(routes) 
  ],  
  providers: [
    DatePipe
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class UserDataModule { }
