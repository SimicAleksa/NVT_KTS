import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
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

  constructor(private reqMaker: APIRequestMaker) {
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
    /*
      Plavo dugme sa slikom auta trigeruje ovu funkciju, ako nema omiljenih ruta dodatih
        za korisnika, samo treba otici na istoriju, pa kliknuti neki red u tabeli i 
        u detaljima voznje dodati preko dugmeta rutu u omiljene i vratiti se na ovu stranicu
    */ 

    /* 
        routeIndex -> indeks rute iz liste "this.routes"
        informacije rute se mogu dobiti sa "this.routes.at(routeIndex)"
        ili "this.routes[routeIndex]"

        odavde mozes da ruterom odes na stranicu za kreiranje voznje i da kao argumente
        url-a stavis id selektovane rute pa posle preuzmes podatke sa back-a ili kako
        god da ti je lakse
    */
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

    let markerLayer1 = L.marker(
          [temRoute.waypoints[0].location[1], temRoute.waypoints[0].location[0]], 
          markerIcon
        );

    let markerLayer2 = L.marker(
      [temRoute.waypoints[1].location[1], temRoute.waypoints[1].location[0]], 
      markerIcon
    );

    markerLayer1.addTo(geoLayerRouteGroup);
    markerLayer2.addTo(geoLayerRouteGroup);
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
