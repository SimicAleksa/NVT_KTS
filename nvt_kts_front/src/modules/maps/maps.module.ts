import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { routes } from './maps-routing';
import { RouterModule } from '@angular/router';
import { SearchRoutesPageComponent } from './pages/search-routes-page/search-routes-page.component';
import { RouteSearchFormComponent } from './components/route-search-form/route-search-form.component';


@NgModule({
  declarations: [
    SearchRoutesPageComponent,
    RouteSearchFormComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class MapsModule { }
