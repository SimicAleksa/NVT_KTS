import { Routes } from '@angular/router';
import { CLIENT_REGISTRATION_URL, CLIENT_ADD_DRIVER_URL, CLIENT_EDIT_PROF_URL, CLIENT_APPROVEC_CHANGES_URL, CLIENT_BLOCK_USER_URL, CLIENT_REG_USR_RIDES_HISTORY_URL, CLIENT_DRIVER_RIDES_HISTORY_URL, CLIENT_ADMIN_RIDES_HISTORY_URL, CLIENT_USER_FAV_ROUTES } from 'src/config/client-urls';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { AddDriverPageComponent } from './pages/add-driver-page/add-driver-page.component';
import { AdminRidesHistoryPageComponent } from './pages/admin-rides-history-page/admin-rides-history-page.component';
import { ApproveChangesPageComponent } from './pages/approve-changes-page/approve-changes-page.component';
import { BlockUserPageComponent } from './pages/block-user-page/block-user-page.component';
import { DriverRidesHistoryPageComponent } from './pages/driver-rides-history-page/driver-rides-history-page.component';
import { EditProfilePageComponent } from './pages/edit-profile-page/edit-profile-page.component';
import { FavouriteRoutesPageComponent } from './pages/favourite-routes-page/favourite-routes-page.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import { UserRidesHistoryPageComponent } from './pages/user-rides-history-page/user-rides-history-page.component';


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
      component : ApproveChangesPageComponent},
    {
      path: CLIENT_BLOCK_USER_URL,
        component: BlockUserPageComponent},

    { path: CLIENT_REG_USR_RIDES_HISTORY_URL, component: UserRidesHistoryPageComponent},
    { path: CLIENT_DRIVER_RIDES_HISTORY_URL, component: DriverRidesHistoryPageComponent},
    { path: CLIENT_ADMIN_RIDES_HISTORY_URL, component: AdminRidesHistoryPageComponent},
    { path: CLIENT_USER_FAV_ROUTES, component: FavouriteRoutesPageComponent}
];



