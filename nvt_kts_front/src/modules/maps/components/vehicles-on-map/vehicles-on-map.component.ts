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
  selector: 'app-vehicles-on-map',
  templateUrl: './vehicles-on-map.component.html',
  styleUrls: ['./vehicles-on-map.component.css']
})
export class VehiclesOnMapComponent implements OnInit {
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

  drivers:any= {};
  rides: any = {};
  mainGroup: LayerGroup[] = [];
  private stompClient: any;
  constructor(private mapService: MapService) { }
  // private geoLayerRouteGroup: LayerGroup = new LayerGroup();
  private geoLayerRouteGroupDRIVERSTHATDONTDRIVE: LayerGroup = new LayerGroup();

  ngOnInit(): void {
    this.mapService.getAllActiveDrivers().subscribe((ret)=>{
      for(let driver of ret){
        console.log(driver)
          // let geoLayerRouteGroup: LayerGroup = new LayerGroup();
          let markerLayer = marker([driver.currentCoords.latitude,driver.currentCoords.longitude], {
            icon: icon({
              iconUrl: './assets/images/carOnMap.png',
              iconSize: [35, 45],
              iconAnchor: [18, 45],
          }),
        });
        markerLayer.addTo(this.geoLayerRouteGroupDRIVERSTHATDONTDRIVE);
        markerLayer.bindPopup(
          '<div class="card text-white bg-success mb-0" style="max-width: 13rem;">\n' +
                  '  <div class="card-body">\n' +
                  '    <h5 class="card-title">'+driver.id+'</h5>\n' +
                  '  </div>\n' +
                  '  <div class="card-body">\n' +
                  '    <a href="#" class="card-link text-white fs-5">Schedule</a>\n' +
                  '  </div>\n' +
                  '</div>')
        this.drivers[driver.id] = markerLayer;
        this.mainGroup = [...this.mainGroup, this.geoLayerRouteGroupDRIVERSTHATDONTDRIVE];
      }
    })

    this.initializeWebSocketConnection();
    this.mapService.getAllActiveRides().subscribe((ret) => {
      for (let ride of ret) {
        let color = Math.floor(Math.random() * 16777215).toString(16);
        let geoLayerRouteGroup: LayerGroup = new LayerGroup();
        let temRoute = JSON.parse(ride.route.routeJSON)
        if("routesIndex" in temRoute){
          let step = JSON.parse(ride.route.routeJSON)['coordinates'];
         
          var polyline =L.polyline(step)
          polyline.addTo(geoLayerRouteGroup)
          this.rides[ride.id] = geoLayerRouteGroup;
        }
        else{
          for (let step of temRoute['routes'][0]['legs'][0]['steps']) {
            console.log(step.geometry)
            let routeLayer = geoJSON(step.geometry);
            routeLayer.setStyle({ color: `#${color}` });
            routeLayer.addTo(geoLayerRouteGroup);
            this.rides[ride.id] = geoLayerRouteGroup;
          }
        }
        this.mapService.getDriverFromRide(ride.driver.toString()).subscribe((ret)=>{
          let driver = ret;
          let markerLayer = marker([driver.currentCoords.latitude,driver.currentCoords.longitude], {
            icon: icon({
              iconUrl: './assets/images/carOnMap.png',
              iconSize: [35, 45],
              iconAnchor: [18, 45],
          }),
        });
        markerLayer.addTo(geoLayerRouteGroup);
        this.drivers[driver.id] = markerLayer;
        this.mainGroup = [...this.mainGroup, geoLayerRouteGroup];
        })
      }
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
      let existingDriver = this.drivers[driver.id];
      existingDriver.setLatLng([driver.currentCoords.latitude, driver.currentCoords.longitude]);
      existingDriver.update();
    });
    this.stompClient.subscribe('/map-updates/new-ride', (message: { body: string }) => {
      let geoLayerRouteGroup: LayerGroup = new LayerGroup();
      let ride: Ride = JSON.parse(message.body);
      let color = Math.floor(Math.random() * 16777215).toString(16);
      for (let step of JSON.parse(ride.route.routeJSON)['routes'][0]['legs'][0]['steps']) {
        let routeLayer = geoJSON(step.geometry);
        routeLayer.setStyle({ color: `#${color}` });
        routeLayer.addTo(geoLayerRouteGroup);
        this.rides[ride.id] = geoLayerRouteGroup;
      }
      this.mainGroup = [...this.mainGroup, geoLayerRouteGroup];
    });

    this.stompClient.subscribe('/map-updates/ended-ride', (message: { body: string }) => {
      let ride: Ride = JSON.parse(message.body);
      this.mainGroup = this.mainGroup.filter((lg: LayerGroup) => lg !== this.rides[ride.id]);
      // delete this.drivers[ride.driver.id];
      // delete this.rides[ride.id];
      window.location.reload();
    });


    this.stompClient.subscribe('/map-updates/change-RIDE-to-in-PROGRESS', (message: { body: string }) => {
      let ride: Ride = JSON.parse(message.body);
      this.mainGroup = this.mainGroup.filter((lg: LayerGroup) => lg !== this.rides[ride.id]);
      window.location.reload();
      // delete this.drivers[ride.driver.id];
      // delete this.rides[ride.id];
    });


    // this.stompClient.subscribe('/map-updates/delete-all-rides', (message: { body: string }) => {
    //   // this.drivers = {};
    //   // this.rides = {};
    //   // this.mainGroup = [];
    // });
  }

}
