import { Routes } from '@angular/router';
import { SearchRoutesPageComponent } from './pages/search-routes-page/search-routes-page.component';
import { ActiveVehicleComponent } from './components/active-vehicle/active-vehicle.component';
export const routes: Routes = [
  {
    path:"routeSearch",
    component : SearchRoutesPageComponent},
  {
    path:"activeVehicle",
    component :ActiveVehicleComponent
  }

];



