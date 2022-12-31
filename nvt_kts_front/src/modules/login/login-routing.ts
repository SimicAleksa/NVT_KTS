import { Routes } from '@angular/router';
import { CLIENT_LOGIN_URL, CLIENT_RECOVER_PASS_URL, CLIENT_RESET_PASS_URL } from 'src/config/client-urls';
import { ForgottenPasswordPageComponent } from './pages/forgotten-password-page/forgotten-password-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { ResetPasswordPageComponent } from './pages/reset-password-page/reset-password-page.component';


export const routes: Routes = [
    { path: CLIENT_LOGIN_URL, component: LoginPageComponent },
    { path: CLIENT_RECOVER_PASS_URL, component: ForgottenPasswordPageComponent },
    { path: CLIENT_RESET_PASS_URL, component: ResetPasswordPageComponent }
];