import { Component, OnInit,Input} from '@angular/core';
import {Route, Router} from '@angular/router';
import * as L from 'leaflet';
import GeocoderControl, {Geocoder, geocoders} from 'leaflet-control-geocoder';

import 'leaflet-routing-machine'
import { MapLocation } from 'src/modules/app/model/mapLocation';
import {control, marker} from "leaflet";
import {mark} from "@angular/compiler-cli/src/ngtsc/perf/src/clock";


@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements OnInit {
  private map!: L.Map;
  private centroid: L.LatLngExpression = [44.0165, 21.0059];

  // @Input() selectedStartLocation!:MapLocation;
  // @Input() selectedEndLocation!:MapLocation;

  private initMap(): void {
    this.map = L.map('map', {
      center: this.centroid,
      zoom: 7,
    });

    const tiles = L.tileLayer(
      'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      {
        maxZoom: 17,
        minZoom: 1,
        attribution:
          '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
      }
    );
    this.map.addLayer(tiles)
  }

  constructor() {}

  ngOnInit(): void {
    this.initMap()
    var marker = L.marker([-1000, -1000]).addTo(this.map);


    L.Routing.control({
      router: L.Routing.osrmv1({
        serviceUrl: 'https://routing.openstreetmap.de/routed-car/route/v1'
      }),
      fitSelectedRoutes:true,
      routeWhileDragging:false,
      geocoder: new geocoders.Nominatim(),
      waypointMode:'snap'
    })
      .on('routeselected', function(e) {
        var route = e.route;
        // console.log('Showing route between waypoints:\n' + JSON.stringify(route.instructions, null, 2));
        console.log(route)
        route.coordinates.forEach(function (coord: L.LatLng,index: number){
            setTimeout(()=>{
              marker.setLatLng([coord.lat,coord.lng]);
            },100*index)
          })
      })
      .addTo(this.map)
  }
  ngOnChanges(): void {
  }
}
