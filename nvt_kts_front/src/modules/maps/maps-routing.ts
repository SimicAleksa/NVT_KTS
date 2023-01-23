import { Routes } from '@angular/router';
import { SearchRoutesPageComponent } from './pages/search-routes-page/search-routes-page.component';
import { ActiveVehicleComponent } from './components/active-vehicle/active-vehicle.component';
import { VehiclesOnMapComponent } from './components/vehicles-on-map/vehicles-on-map.component';
import { SimpleRouteSearchComponent } from './components/simple-route-search/simple-route-search.component';
import { SimpleRoutesSearchPageComponent } from './pages/simple-routes-search-page/simple-routes-search-page.component';
import { TrackIncDriverComponent } from './components/track-inc-driver/track-inc-driver.component';
export const routes: Routes = [
  {
    path:"routeSearch",
    component : SearchRoutesPageComponent
  },
  {
    path:"simpleRouteSearch",
    component : SimpleRoutesSearchPageComponent
  },
  {
    path:"activeVehicle",
    component :ActiveVehicleComponent
  },
  {
    path:"incomingDriver",
    component :TrackIncDriverComponent
  },
  {
    path:"allVehiclesOnMap",
    component :VehiclesOnMapComponent
  }

];



