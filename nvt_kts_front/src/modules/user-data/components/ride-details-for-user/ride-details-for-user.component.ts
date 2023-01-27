import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import * as L from 'leaflet';
import { geoJSON, latLng, LayerGroup, tileLayer } from 'leaflet';
import { APIRequestMaker } from 'src/utils/api-request-maker';

@Component({
  selector: 'app-ride-details-for-user',
  templateUrl: './ride-details-for-user.component.html',
  styleUrls: ['./ride-details-for-user.component.css']
})
export class RideDetailsForUserComponent implements OnInit {
  @Output() showReviewsBtnClickedSignal = new EventEmitter<number>();
  @Output() errSignal = new EventEmitter<string>();
  @Output() successSignal = new EventEmitter<string>();

  public mainGroup: LayerGroup[] = [];

  public showPanel: boolean;
  public options: any;
  public rideLayer: L.LayerGroup;

  public details: any;
  driver: any;
  
  constructor(private reqMaker: APIRequestMaker) { 
    this.showPanel = false;
    this.details = null;

    this.options = {
      layers: [
        tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
          maxZoom: 18,
          attribution: '...',
        }),
      ],
      zoom: 14,
      center: latLng(45.253434, 19.831323),
    };
  }

  ngOnInit(): void {
  }

  setUpData(details: any): void {
    console.log(details);
    this.details = details;
    this.setupRouteLineOnMap()
    this.showPanel = true;
  }

  goToNewRidePage(): void {
    //TO DO
  }

  showDriverReviews(): void {
    this.showReviewsBtnClickedSignal.emit(this.details.driver.id);
  }

  saveRouteToFavourites(): void {
    this.reqMaker.createNewFavoriteRouteRequest(this.details.ride.route.routeId).subscribe(this.getObservable());
  }

  getObservable() {
    return {
      next: (retData: any) => {
      },
      error: (err: any) => {
        if (err.status === 406)
          this.emitError("This route is already in your favourites.");
      },
      complete: () => {
        this.emitSuccess("Route has been successfully added to your favourites.");
      }
    }
  }

  emitError(errMessage: string): void {
    this.errSignal.emit(errMessage);
  }

  emitSuccess(succMess: string): void {
    this.successSignal.emit(succMess);
  }

  setupRouteLineOnMap() {
    let color = Math.floor(8000000).toString(16);
    let geoLayerRouteGroup: LayerGroup = new LayerGroup();
    let temRoute = this.details.ride.route.routeJSON;

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
  }
 
  
}
