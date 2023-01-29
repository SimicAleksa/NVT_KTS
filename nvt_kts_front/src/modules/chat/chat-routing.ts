import { Routes } from '@angular/router';
import { ChatPageComponent } from './pages/chat-page/chat-page.component';
import { ConfirmRegistrationPageComponent } from './pages/confirm-registration-page/confirm-registration-page.component';

export const routes: Routes = [
    {
      path:"chatPage",
      component : ChatPageComponent},
    {
      path:"confirmRegistration/:email",
      component : ConfirmRegistrationPageComponent},
   
];



