import { Routes } from '@angular/router';
import { RoleGuard } from '../app/guards/role.guard';
import { ChatPageComponent } from './pages/chat-page/chat-page.component';
import { ConfirmRegistrationPageComponent } from './pages/confirm-registration-page/confirm-registration-page.component';

export const routes: Routes = [
    {
      path:"chatPage",
      component : ChatPageComponent,
      canActivate: [RoleGuard],
      data:{expectedRoles:"USER|DRIVER|ADMIN"}
    },
    {
      path:"confirmRegistration/:email",
      component : ConfirmRegistrationPageComponent},
   
];



