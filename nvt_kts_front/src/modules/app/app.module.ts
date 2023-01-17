import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { Routes } from '@angular/router';
import { MapsModule } from '../maps/maps.module';
import { UserDataModule } from '../user-data/user-data.module';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { HttpClientModule } from '@angular/common/http';
import { ChatModule } from '../chat/chat.module';
import { ReportsModule } from '../reports/reports.module';

const appRoutes: Routes = [{ path: '', component: AppComponent }];

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MapsModule,
    UserDataModule,
    ChatModule,
    ReportsModule,
    FormsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
