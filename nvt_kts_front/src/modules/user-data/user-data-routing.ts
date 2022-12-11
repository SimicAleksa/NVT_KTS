import { Routes } from '@angular/router';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { AddDriverPageComponent } from './pages/add-driver-page/add-driver-page.component';
import { ApproveChangesPageComponent } from './pages/approve-changes-page/approve-changes-page.component';
import { EditProfilePageComponent } from './pages/edit-profile-page/edit-profile-page.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
export const routes: Routes = [
  {
    path:"registrationPage",
    component : RegistrationPageComponent},
    {
      path:"addDriverPage",
      component : AddDriverPageComponent},
    {
      path:"editProfilePage",
      component : EditProfilePageComponent},
    {
      path:"approveChangesPage",
      component : ApproveChangesPageComponent}

];



