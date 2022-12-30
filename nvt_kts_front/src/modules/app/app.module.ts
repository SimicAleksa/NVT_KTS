import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { Routes } from '@angular/router';
import { MapsModule } from '../maps/maps.module';
import { UserDataModule } from '../user-data/user-data.module';
import { FormsModule } from '@angular/forms';
import { LoginModule } from '../login/login.module';
import { PopUpModule } from '../pop-up/pop-up.module';
import { HttpClientModule } from '@angular/common/http';

const appRoutes: Routes = [{ path: '', component: AppComponent }];

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule, 
    HttpClientModule, 
    FormsModule, 
    AppRoutingModule, 
    MapsModule, 
    UserDataModule,
    LoginModule, 
    PopUpModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
