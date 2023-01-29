import { Routes } from '@angular/router';
import { SearchRoutesPageComponent } from './pages/search-routes-page/search-routes-page.component';
import { ActiveVehicleComponent } from './components/active-vehicle/active-vehicle.component';
import { VehiclesOnMapComponent } from './components/vehicles-on-map/vehicles-on-map.component';
import { SimpleRouteSearchComponent } from './components/simple-route-search/simple-route-search.component';
import { SimpleRoutesSearchPageComponent } from './pages/simple-routes-search-page/simple-routes-search-page.component';
import { TrackIncDriverComponent } from './components/track-inc-driver/track-inc-driver.component';
import { TrackRouteComponent } from './components/track-route/track-route.component';
import { TempForQueryComponent } from './components/temp-for-query/temp-for-query.component'; 
import { RoleGuard } from '../app/guards/role.guard';
export const routes: Routes = [
  {
    path:"routeSearch",
    component : SearchRoutesPageComponent,
    canActivate: [RoleGuard],
    data:{expectedRoles:"USER"}
  },
  {
    path:"simpleRouteSearch",
    component : SimpleRoutesSearchPageComponent,
    canActivate: [RoleGuard],
    data:{expectedRoles:"USER"}
  },
  {
    path:"activeVehicle",
    component :ActiveVehicleComponent,
  },
  {
    path:"driverRouting",
    component :TrackRouteComponent,
    canActivate: [RoleGuard],
    data:{expectedRoles:"USER"}
  },
  {
    path:"incomingDriver",
    component :TrackIncDriverComponent,
    canActivate: [RoleGuard],
    data:{expectedRoles:"USER"}
  },
  {
    path:"allVehiclesOnMap",
    component :VehiclesOnMapComponent,
    canActivate: [RoleGuard],
    data:{expectedRoles:"USER"}
  },
  {
    path:"tempForQuery",
    component :TempForQueryComponent
  },

];



