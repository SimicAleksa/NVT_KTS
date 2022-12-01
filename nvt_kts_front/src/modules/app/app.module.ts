import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { Routes } from '@angular/router';
import { MapsModule } from '../maps/maps.module';
import { FormsModule } from '@angular/forms';

const appRoutes: Routes = [{ path: '', component: AppComponent }];

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, FormsModule, AppRoutingModule, MapsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}