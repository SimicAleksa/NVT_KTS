import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { routes } from './maps-routing';
import { RouterModule } from '@angular/router';
import { SearchRoutesPageComponent } from './pages/search-routes-page/search-routes-page.component';
import { RouteSearchFormComponent } from './components/route-search-form/route-search-form.component';
import { MapComponent } from './components/map/map.component';
import { FormsModule } from '@angular/forms';
import { ActiveVehicleComponent } from './components/active-vehicle/active-vehicle.component';
import { VehicleCardComponent } from './components/vehicle-card/vehicle-card.component';
import { VehiclesOnMapComponent } from './components/vehicles-on-map/vehicles-on-map.component';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';

@NgModule({
  declarations: [
    SearchRoutesPageComponent,
    RouteSearchFormComponent,
    MapComponent,
    ActiveVehicleComponent,
    VehicleCardComponent,
    VehiclesOnMapComponent
  ],
  imports: [CommonModule, RouterModule.forChild(routes),FormsModule,LeafletModule],
})
export class MapsModule {}
