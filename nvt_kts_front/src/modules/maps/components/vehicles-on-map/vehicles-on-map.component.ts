import { Component, OnInit } from '@angular/core';
// import * as L from "leaflet";
import { latLng, tileLayer, marker, geoJSON, LayerGroup, icon } from 'leaflet';
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
  // private map!: L.Map;
  // private centroid: L.LatLngExpression = [44.0165, 21.0059];

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

  drivers: any = {};
  rides: any = {};
  mainGroup: LayerGroup[] = [];
  private stompClient: any;

  // public allVehicleLocations: L.LatLngExpression[] = [
  //   [44.0165, 20.0059],
  //   [44.0165, 21.0059],
  //   // [45.0165, 19.9059],
  //   // [45.6165, 20.5],
  //   // [43.0165, 21.0059],
  // ]
  // public vehicles_1: IDriverVehicleDTO[] = [
  //   {
  //     driverId: 1,
  //     carType: "Coupe",
  //     babyAllowed: false,
  //     petAllowed:  false,
  //     isDriverFree: true,
  //     active:  true,
  //     vehicleCoordsLat: 44.0165,
  //     vehicleCoordsLen: 20.0059
  //   },
  //   {
  //     driverId: 2,
  //     carType: "SUV",
  //     babyAllowed: false,
  //     petAllowed:  false,
  //     isDriverFree: true,
  //     active:  true,
  //     vehicleCoordsLat: 44.0165,
  //     vehicleCoordsLen: 21.0059
  //   }
  // ];

  // private greenIcon: L.Icon = L.icon({
  //   iconUrl: './assets/images/carOnMap.png',
  //   iconSize:     [35, 35], // size of the icon
  //   iconAnchor:   [35, 35], // point of the icon which will correspond to marker's location
  //   popupAnchor:  [-3, -16] // point from which the popup should open relative to the iconAnchor
  // });

  // private initMap(): void {
  //   this.map = L.map('map', {
  //     center: this.centroid,
  //     zoom: 7,
  //   });
  //   const tiles = L.tileLayer(
  //     'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
  //     {
  //       maxZoom: 17,
  //       minZoom: 1,
  //       attribution:
  //         '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
  //     }
  //   );
  //   this.map.addLayer(tiles)
  // }

  constructor(private mapService: MapService) { }

  ngOnInit(): void {

    // this.initMap()


    // for(let vehicle of this.vehicles_1){
    //   let marker = L.marker([vehicle.vehicleCoordsLat,vehicle.vehicleCoordsLen]).setIcon(this.greenIcon).addTo(this.map);

    //   // POGLEDAJ GDE JE OVDE PROBLEM I ZASTO NECE
    //   // treba dodati u zavisnosti od toga da li su vozila slobodna ili ne prikazati crvene ili zelene boje
    //   // marker.bindPopup('<app-vehicle-card [vehicle]=\"'+vehicle+'\"></app-vehicle-card>')
    //   marker.bindPopup(
    //     '<div class="card text-white bg-success mb-0" style="max-width: 13rem;">\n' +
    //            '  <div class="card-body">\n' +
    //            '    <h5 class="card-title">'+vehicle.carType+'</h5>\n' +
    //            '  </div>\n' +
    //            '  <ul class="list-group list-group-flush">\n' +
    //            '    <li class="list-group-item bg-success text-white">Baby allowed - '+vehicle.babyAllowed+'</li>\n' +
    //            '    <li class="list-group-item bg-success text-white">Pet allowed - '+vehicle.petAllowed+'</li>\n' +
    //            '    <li class="list-group-item bg-success text-white">Is vehicle free - '+vehicle.isDriverFree+'</li>\n' +
    //            '  </ul>\n' +
    //            '  <div class="card-body">\n' +
    //            '    <a href="#" class="card-link text-white fs-5">Schedule</a>\n' +
    //            '  </div>\n' +
    //            '</div>')

    //   console.log(vehicle)

    //   // this.mapService.majmun();
    // }

    this.initializeWebSocketConnection();
    this.mapService.getAllActiveRides().subscribe((ret) => {
      for (let ride of ret) {
        let color = Math.floor(Math.random() * 16777215).toString(16);
        let geoLayerRouteGroup: LayerGroup = new LayerGroup();
        for (let step of JSON.parse(ride.routeJSON)['routes'][0]['legs'][0]['steps']) {
          let routeLayer = geoJSON(step.geometry);
          routeLayer.setStyle({ color: `#${color}` });
          routeLayer.addTo(geoLayerRouteGroup);
          this.rides[ride.id] = geoLayerRouteGroup;
        }
        let markerLayer = marker([ride.driver.longitude, ride.driver.latitude], {
          icon: icon({
            iconUrl: './assets/images/carOnMap.png',
            iconSize: [35, 45],
            iconAnchor: [18, 45],
          }),
        });
        markerLayer.addTo(geoLayerRouteGroup);
        this.drivers[ride.driver.id] = markerLayer;
        this.mainGroup = [...this.mainGroup, geoLayerRouteGroup];
      }
    });
  }

  // ngOnChanges(): void{
  // }

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
      console.log(driver);
      console.log(this.drivers);
      console.log(existingDriver);
      existingDriver.setLatLng([driver.longitude, driver.latitude]);
      existingDriver.update();
    });
    this.stompClient.subscribe('/map-updates/new-ride', (message: { body: string }) => {
      let ride: Ride = JSON.parse(message.body);
      console.log(ride)
      let geoLayerRouteGroup: LayerGroup = new LayerGroup();
      let color = Math.floor(Math.random() * 16777215).toString(16);
      for (let step of JSON.parse(ride.routeJSON)['routes'][0]['legs'][0]['steps']) {
        let routeLayer = geoJSON(step.geometry);
        routeLayer.setStyle({ color: `#${color}` });
        routeLayer.addTo(geoLayerRouteGroup);
        this.rides[ride.id] = geoLayerRouteGroup;
      }
      let markerLayer = marker([ride.driver.longitude, ride.driver.latitude], {
        icon: icon({
          iconUrl: './assets/images/carOnMap.png',
          iconSize: [35, 45],
          iconAnchor: [18, 45],
        }),
      });
      markerLayer.addTo(geoLayerRouteGroup);
      this.drivers[ride.driver.id] = markerLayer;
      this.mainGroup = [...this.mainGroup, geoLayerRouteGroup];
    });
    this.stompClient.subscribe('/map-updates/ended-ride', (message: { body: string }) => {
      let ride: Ride = JSON.parse(message.body);
      this.mainGroup = this.mainGroup.filter((lg: LayerGroup) => lg !== this.rides[ride.id]);
      delete this.drivers[ride.driver.id];
      delete this.rides[ride.id];
    });
    this.stompClient.subscribe('/map-updates/delete-all-rides', (message: { body: string }) => {
      this.drivers = {};
      this.rides = {};
      this.mainGroup = [];
    });
  }

}
