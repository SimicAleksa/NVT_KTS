import { Component, OnInit,Input} from '@angular/core';
import {Route, Router} from '@angular/router';
import * as L from 'leaflet';
import GeocoderControl, {Geocoder, geocoders} from 'leaflet-control-geocoder';

import 'leaflet-routing-machine'
import { MapLocation } from 'src/modules/app/model/mapLocation';
import {control, marker} from "leaflet";
import {mark} from "@angular/compiler-cli/src/ngtsc/perf/src/clock";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-simple-route-search',
  templateUrl: './simple-route-search.component.html',
  styleUrls: ['./simple-route-search.component.css']
})
export class SimpleRouteSearchComponent implements OnInit {

  private map!: L.Map;
  private centroid: L.LatLngExpression = [44.0165, 21.0059];

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

  constructor(private toastr: ToastrService) { }

  ngOnInit(): void {
    this.initMap()
    var marker = L.marker([-1000, -1000]).addTo(this.map);
    let that = this;
    L.Routing.control({
      router: L.Routing.osrmv1({
        serviceUrl: 'https://routing.openstreetmap.de/routed-car/route/v1'
      }),
      fitSelectedRoutes:true,
      lineOptions:
      {
        addWaypoints:false,
        extendToWaypoints:true,
        missingRouteTolerance:0
      },
      routeWhileDragging:false,
      geocoder: new geocoders.Nominatim(),
      waypointMode:'snap',
      showAlternatives:false,
    })
      .on('routeselected', function(e) {
        var route = e.route;
        let ridePrice:Number = parseInt(route.summary.totalDistance) + 100;
        that.toastr.info("Price of selected route wolud be: "+ridePrice + ",00 RSD");
        // console.log('Showing route between waypoints:\n' + JSON.stringify(route.instructions, null, 2));
        console.log(route)
        route.coordinates.forEach(function (coord: L.LatLng,index: number){
            setTimeout(()=>{
              marker.setLatLng([coord.lat,coord.lng]);
            },10*index)
          })
      })
      .addTo(this.map)
  }
  

}
