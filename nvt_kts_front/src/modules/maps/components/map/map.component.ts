import { Component, OnInit,Input} from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements OnInit {
  private map!: L.Map;
  private centroid: L.LatLngExpression = [42.3601, -71.0589]; //

  @Input() selectedLocation:any // IZBACI ANY

  private initMap(): void {
    this.map = L.map('map', {
      center: this.centroid,
      zoom: 12,
    });

    const tiles = L.tileLayer(
      'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      {
        maxZoom: 18,
        minZoom: 10,
        attribution:
          '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
      }
    );

    console.log("AAAAAAAAAAAAAAAAAAA");
    console.log(this.selectedLocation);
    const position = new L.LatLng(this.selectedLocation.lat, this.selectedLocation.lon);
    this.map.flyTo(position, 10);
    tiles.addTo(this.map);
  }

  constructor() {}

  ngOnInit(): void {
    this.initMap();
  }
}
