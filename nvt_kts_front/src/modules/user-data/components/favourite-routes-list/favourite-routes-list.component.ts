import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import * as L from 'leaflet';
import { geoJSON, latLng, LayerGroup, tileLayer } from 'leaflet';
import { APIRequestMaker } from 'src/utils/api-request-maker';

@Component({
  selector: 'app-favourite-routes-list',
  templateUrl: './favourite-routes-list.component.html',
  styleUrls: ['./favourite-routes-list.component.css']
})
export class FavouriteRoutesListComponent implements OnInit {
  @Output() succSignal = new EventEmitter<string>();
  
  @Input() routes: Array<any>;
  
  public mainGroup: LayerGroup[] = [];

  public options: any;
  public rideLayer: L.LayerGroup;
  
  public selectedRoute: number;
  private routeLayerDeletable: boolean;

  constructor(private reqMaker: APIRequestMaker,private router:Router) {
    this.routes = new Array;
    this.selectedRoute = 0;
    this.routeLayerDeletable = false;

    this.options = {
      layers: [
        tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
          maxZoom: 18,
          attribution: '...',
        }),
      ],
      zoom: 12,
      center: latLng(45.253434, 19.831323),
    };

   }

  ngOnInit(): void {
  }

  goToNewRidePageForTheSelectedRoute(routeIndex: number): void {
    this.router.navigate(
      ['/maps/routeSearch'],
      { queryParams: { order: String(this.routes.at(routeIndex)['routeId']) } }
    );

  } 

  removeRouteFromFavourites(routeIndex: number): void {
    this.reqMaker.createRemoveRouteFromFavouritesRequest(this.routes.at(routeIndex).routeId).subscribe(this.getObservable());
  }

  getObservable() {
    return {
      next: (retData: any) => {
      },
      error: (err: any) => {
      },
      complete: () => {
        this.emitSuccess("The route has been successfully removed from favourites.");
        
      }
    }
  }

  setSelected(selected: number): void {
    this.selectedRoute = selected;
  }

  setupRouteLineOnMap(route: any): void {
    if (this.routeLayerDeletable)
      this.removePrevRoute();

    let color = Math.floor(8000000).toString(16);
    let geoLayerRouteGroup: LayerGroup = new LayerGroup();
    let temRoute = route.routeJSON;

    if("routesIndex" in temRoute){
      var polyline = L.polyline(temRoute['coordinates'])
      polyline.addTo(geoLayerRouteGroup)
      this.rideLayer = geoLayerRouteGroup;
    }
    else{
      for (let step of temRoute['routes'][0]['legs'][0]['steps']) {
        let routeLayer = geoJSON(step.geometry);
        routeLayer.setStyle({ color: `#${color}` });
        routeLayer.addTo(geoLayerRouteGroup);
        this.rideLayer = geoLayerRouteGroup;
      }
    }

    let markerIcon = {
      icon: L.icon({
        iconUrl: './assets/images/locationMarker.png',
        iconSize: [35, 45],
        iconAnchor: [18, 45],
      })
    };;

    var markerLayer1;
    var markerLayer2;
    if("routesIndex" in temRoute){
      markerLayer1 = L.marker(
        [temRoute.waypoints[0].latLng['lat'], temRoute.waypoints[0].latLng['lng']], 
        markerIcon
      );

      markerLayer2 = L.marker(
        [temRoute.waypoints[temRoute.waypoints.length-1].latLng['lat'], temRoute.waypoints[temRoute.waypoints.length-1].latLng['lng']], 
        markerIcon
      );
    }
    else{
      markerLayer1 = L.marker(
            [temRoute.waypoints[0].location[1], temRoute.waypoints[0].location[0]], 
            markerIcon
          );

      markerLayer2 = L.marker(
        [temRoute.waypoints[temRoute.waypoints.length-1].location[1], temRoute.waypoints[temRoute.waypoints.length-1].location[0]], 
        markerIcon
      );
    }
    markerLayer1?.addTo(geoLayerRouteGroup);
    markerLayer2?.addTo(geoLayerRouteGroup);
    this.mainGroup = [...this.mainGroup, geoLayerRouteGroup];
    this.routeLayerDeletable = true;
  }

  removePrevRoute() {
    this.mainGroup = this.mainGroup.slice(0, -1);
  }

  emitSuccess(errMessage: string): void {
    this.succSignal.emit(errMessage);
  }
  
}
