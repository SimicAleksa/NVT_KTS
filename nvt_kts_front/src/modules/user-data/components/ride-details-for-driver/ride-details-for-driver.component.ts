import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import * as L from 'leaflet';
import { geoJSON, latLng, LayerGroup, tileLayer } from 'leaflet';

@Component({
  selector: 'app-ride-details-for-driver',
  templateUrl: './ride-details-for-driver.component.html',
  styleUrls: ['./ride-details-for-driver.component.css']
})
export class RideDetailsForDriverComponent implements OnInit {
  @Output() showReviewsBtnClickedSignal = new EventEmitter<number>();

  public mainGroup: LayerGroup[] = [];

  public showPanel: boolean;
  public options: any;
  public rideLayer: L.LayerGroup;

  public details: any;
  driver: any;

  
  constructor() { 
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
    this.details = details;
    this.setupRouteLineOnMap()
    this.showPanel = true;
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
