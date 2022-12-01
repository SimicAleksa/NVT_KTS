import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { routes } from './maps-routing';
import { RouterModule } from '@angular/router';
import { SearchRoutesPageComponent } from './pages/search-routes-page/search-routes-page.component';
import { RouteSearchFormComponent } from './components/route-search-form/route-search-form.component';
import { MapComponent } from './components/map/map.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    SearchRoutesPageComponent,
    RouteSearchFormComponent,
    MapComponent
  ],
  imports: [CommonModule, RouterModule.forChild(routes),FormsModule],
})
export class MapsModule {}