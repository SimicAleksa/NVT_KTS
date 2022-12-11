import { Routes } from '@angular/router';
import { ForgottenPasswordPageComponent } from './pages/forgotten-password-page/forgotten-password-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';


export const routes: Routes = [
    { path: "login", component: LoginPageComponent },
    { path: "login/recover-password", component: ForgottenPasswordPageComponent }
];