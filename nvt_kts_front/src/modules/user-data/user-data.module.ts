import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BlockUserPageComponent } from './pages/block-user-page/block-user-page.component';
import { BlockUserComponent } from './components/block-user/block-user.component';
import { UserRidesHistoryPageComponent } from './pages/user-rides-history-page/user-rides-history-page.component';
import { RideHistoryTableComponent } from './components/ride-history-table/ride-history-table.component';
import { RideDetailsForUserComponent } from './components/ride-details-for-user/ride-details-for-user.component';
import { DriverReviewsForUserComponent } from './components/driver-reviews-for-user/driver-reviews-for-user.component';
import { StarRatingModule } from 'angular-star-rating';
import { PopUpModule } from '../pop-up/pop-up.module';
import { StarRatingConfigService } from 'angular-star-rating';
import { DriverRidesHistoryPageComponent } from './pages/driver-rides-history-page/driver-rides-history-page.component';
import { RideDetailsForDriverComponent } from './components/ride-details-for-driver/ride-details-for-driver.component';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { AdminRidesHistoryPageComponent } from './pages/admin-rides-history-page/admin-rides-history-page.component';
import { RideDetailsForAdminComponent } from './components/ride-details-for-admin/ride-details-for-admin.component';
import { FavouriteRoutesPageComponent } from './pages/favourite-routes-page/favourite-routes-page.component';
import { FavouriteRoutesListComponent } from './components/favourite-routes-list/favourite-routes-list.component';
import { EditProfileDriverComponent } from './components/edit-profile-driver/edit-profile-driver.component';
import { EditProfileAdminComponent } from './components/edit-profile-admin/edit-profile-admin.component';
import { EditProfileAdminPageComponent } from './pages/edit-profile-admin-page/edit-profile-admin-page.component';
import { EditProfileDriverPageComponent } from './pages/edit-profile-driver-page/edit-profile-driver-page.component';



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
    BlockUserPageComponent,
    BlockUserComponent,
    UserRidesHistoryPageComponent,
    RideHistoryTableComponent,
    RideDetailsForUserComponent,
    DriverReviewsForUserComponent,
    DriverRidesHistoryPageComponent,
    RideDetailsForDriverComponent,
    AdminRidesHistoryPageComponent,
    RideDetailsForAdminComponent,
    FavouriteRoutesPageComponent,
    FavouriteRoutesListComponent,
    EditProfileDriverComponent,
    EditProfileAdminComponent,
    EditProfileAdminPageComponent,
    EditProfileDriverPageComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    StarRatingModule,
    PopUpModule,
    LeafletModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    DatePipe,
    StarRatingConfigService
  ]
})
export class UserDataModule { }
