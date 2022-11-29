import { Component, OnInit,Input} from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements OnInit {
  private map!: L.Map;
  private centroid: L.LatLngExpression = [44.0165, 21.0059]; //

  @Input() selectedLocation:any // IZBACI ANY

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
      console.log(this.selectedLocation);
      const position = new L.LatLng(this.selectedLocation.lat, this.selectedLocation.lon);

      this.map.flyTo(position, 15);

      let marker = L.marker(position);
      marker.bindPopup(this.selectedLocation.display_name).openPopup();
      marker.addTo(this.map);
    }
  }
}
