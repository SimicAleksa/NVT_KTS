import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { ForgottenPasswordPageComponent } from './pages/forgotten-password-page/forgotten-password-page.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { ForgottenPasswordFormComponent } from './components/forgotten-password-form/forgotten-password-form.component';
import { RouterModule } from '@angular/router';
import { routes } from './login-routing';


@NgModule({
  declarations: [
    LoginPageComponent,
    ForgottenPasswordPageComponent,
    LoginFormComponent,
    ForgottenPasswordFormComponent
  ],
  imports: [
    CommonModule, 
    RouterModule.forChild(routes)
  ]
})
export class LoginModule { }
