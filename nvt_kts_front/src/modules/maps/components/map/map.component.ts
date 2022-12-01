import { Component, OnInit,Input} from '@angular/core';
import { Router } from '@angular/router';
import * as L from 'leaflet';
import 'leaflet-routing-machine'
import { MapLocation } from 'src/modules/app/model/mapLocation';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements OnInit {
  private map!: L.Map;
  private centroid: L.LatLngExpression = [44.0165, 21.0059];

  @Input() selectedStartLocation!:MapLocation;
  @Input() selectedEndLocation!:MapLocation;

  private startPosition!: L.LatLng;
  private endPosition!: L.LatLng;

  private startMarker!: L.Marker;
  private endMarker!: L.Marker;

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
    // tiles.addTo(this.map);
  }

  constructor() {}

  ngOnInit(): void {
  }
  ngOnChanges(): void{
    if(this.map==null)
      this.initMap();
    else
    {
      if(this.selectedStartLocation!=null)
      {
        this.startPosition = new L.LatLng(parseFloat(this.selectedStartLocation.lat), parseFloat(this.selectedStartLocation.lon));

        this.map.flyTo(this.startPosition, 15);

        this.startMarker = L.marker(this.startPosition);
        this.startMarker.bindPopup(this.selectedStartLocation.display_name).openPopup();
        this.startMarker.addTo(this.map);
      }
      if(this.selectedEndLocation!=null)
      {
        this.endPosition = new L.LatLng(parseFloat(this.selectedEndLocation.lat), parseFloat(this.selectedEndLocation.lon));

        this.map.flyTo(this.endPosition, 15);

        this.endMarker = L.marker(this.endPosition);
        this.endMarker.bindPopup(this.selectedEndLocation.display_name).openPopup();
        this.endMarker.addTo(this.map);
      }
      if (this.selectedStartLocation!=null && this.selectedEndLocation!=null)
      {
        L.Routing.control({
          router: L.Routing.osrmv1({
            serviceUrl: 'https://router.project-osrm.org/route/v1/'
          }),
          showAlternatives:true,
          fitSelectedRoutes:true,
          routeWhileDragging:false,
          waypointMode:'snap',
          waypoints:[
            this.startPosition,
            this.endPosition
          ]
      }).addTo(this.map)
      }
    }
  }
}
