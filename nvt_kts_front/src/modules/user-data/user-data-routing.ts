import { Routes } from '@angular/router';
import { CLIENT_REGISTRATION_URL, CLIENT_ADD_DRIVER_URL, CLIENT_EDIT_PROF_URL, CLIENT_APPROVEC_CHANGES_URL } from 'src/config/client-urls';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { AddDriverPageComponent } from './pages/add-driver-page/add-driver-page.component';
import { ApproveChangesPageComponent } from './pages/approve-changes-page/approve-changes-page.component';
import { EditProfilePageComponent } from './pages/edit-profile-page/edit-profile-page.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
export const routes: Routes = [
  {
    path: CLIENT_REGISTRATION_URL,
    component : RegistrationPageComponent},
    {
      path: CLIENT_ADD_DRIVER_URL,
      component : AddDriverPageComponent},
    {
      path: CLIENT_EDIT_PROF_URL,
      component : EditProfilePageComponent},
    {
      path: CLIENT_APPROVEC_CHANGES_URL,
      component : ApproveChangesPageComponent}

];


