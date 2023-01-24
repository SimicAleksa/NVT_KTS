import { Component, OnInit } from '@angular/core';
import * as L from "leaflet";
import { latLng, tileLayer, marker, geoJSON, LayerGroup, icon, gridLayer } from 'leaflet';
import { MapService } from '../../services/map.service';
import {IDriverVehicleDTO} from "../active-vehicle/driverWithVehicleDTO";
import { Driver } from '../active-vehicle/Driver';
import { Ride } from '../active-vehicle/Ride';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-track-route',
  templateUrl: './track-route.component.html',
  styleUrls: ['./track-route.component.css']
})
export class TrackRouteComponent implements OnInit {
  options = {
    layers: [
      tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 18,
        attribution: '...',
      }),
    ],
    zoom: 14,
    center: latLng(45.253434, 19.831323),
  };

  drivers:any={};
  rideLayer: L.LayerGroup;
  mainGroup: LayerGroup[] = [];
  driver:Driver;
  ride:Ride;
  private stompClient: any;
  constructor(private mapService: MapService) { }

  ngOnInit(): void {

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // !!!!!!!!!!!!!!!!!VOZAC JE ZAKUCAN DO NJEGA TREBA DOCI PREKO PUTNIKOVIH VOZNJI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    this.initializeWebSocketConnection();
    this.mapService.getPassangersDrivingTheRouteRide("3").subscribe((ret) => {
      this.ride=ret;
      let color = Math.floor(Math.random() * 16777215).toString(16);
      let geoLayerRouteGroup: LayerGroup = new LayerGroup();
      let temRoute = JSON.parse(ret.route.routeJSON)
      if("routesIndex" in temRoute){
        let step = JSON.parse(ret.route.routeJSON)['coordinates'];
        var polyline =L.polyline(step)
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
      this.mapService.getDriverFromRide(ret.driver.toString()).subscribe((ret)=>{
        this.driver = ret;
        let markerLayer = marker([this.driver.currentCoords.latitude,this.driver.currentCoords.longitude], {
          icon: icon({
            iconUrl: './assets/images/carOnMap.png',
            iconSize: [35, 45],
            iconAnchor: [18, 45],
        }),
      });
      markerLayer.addTo(geoLayerRouteGroup);
      this.drivers[this.driver.id] = markerLayer;
      this.mainGroup = [...this.mainGroup, geoLayerRouteGroup];
      })
    });
  }

  initializeWebSocketConnection() {
    let ws = new SockJS('http://localhost:8000/socket');
    this.stompClient = Stomp.over(ws);
    this.stompClient.debug = null;
    let that = this;
    this.stompClient.connect({}, function () {
      that.openGlobalSocket();
    });
  }

  openGlobalSocket() {
    
    this.stompClient.subscribe('/map-updates/update-vehicle-position', (message: { body: string }) => {
      let driver: Driver = JSON.parse(message.body);
      if(driver.id===this.driver.id){
        let existingDriver = this.drivers[driver.id];
        existingDriver.setLatLng([driver.currentCoords.latitude, driver.currentCoords.longitude]);
        existingDriver.update();
      }
    });

    this.stompClient.subscribe('/map-updates/ended-ride', (message: { body: string }) => {
      let ride: Ride = JSON.parse(message.body);
      if(this.ride.id === ride.id){
        this.mainGroup = this.mainGroup.filter((lg: LayerGroup) => lg !== this.rideLayer);
        window.location.reload();
      }
    });
  }
}
