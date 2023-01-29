import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChatPageComponent } from './pages/chat-page/chat-page.component';
import { ChatComponent } from './components/chat/chat.component';
import { RouterModule } from '@angular/router';
import {routes} from './chat-routing';
import { ConfirmRegistrationComponent } from './components/confirm-registration/confirm-registration.component';
import { ConfirmRegistrationPageComponent } from './pages/confirm-registration-page/confirm-registration-page.component';


@NgModule({
  declarations: [
    ChatPageComponent,
    ChatComponent,
    ConfirmRegistrationComponent,
    ConfirmRegistrationPageComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class ChatModule { }
