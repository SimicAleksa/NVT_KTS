import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { ForgottenPasswordPageComponent } from './pages/forgotten-password-page/forgotten-password-page.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { ForgottenPasswordFormComponent } from './components/forgotten-password-form/forgotten-password-form.component';
import { RouterModule } from '@angular/router';
import { routes } from './login-routing';
import { ResetPasswordFormComponent } from './components/reset-password-form/reset-password-form.component';
import { ResetPasswordPageComponent } from './pages/reset-password-page/reset-password-page.component';
import { PopUpModule } from '../pop-up/pop-up.module';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    LoginPageComponent,
    ForgottenPasswordPageComponent,
    LoginFormComponent,
    ForgottenPasswordFormComponent,
    ResetPasswordFormComponent,
    ResetPasswordPageComponent
  ],
  imports: [
    CommonModule, 
    FormsModule,
    RouterModule.forChild(routes),
    PopUpModule
  ]
})
export class LoginModule { }
