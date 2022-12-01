import { Component, OnInit,Output,EventEmitter } from '@angular/core';
import { MapLocation } from 'src/modules/app/model/mapLocation';

@Component({
  selector: 'app-route-search-form',
  templateUrl: './route-search-form.component.html',
  styleUrls: ['./route-search-form.component.css'],
})
export class RouteSearchFormComponent implements OnInit {
  public searchedStartLocationInput!: String;
  public selectedStartLocation!: MapLocation;

  public searchedEndLocationInput!: String;
  public selectedEndLocation!: MapLocation;

  public startResultList!: Array<MapLocation>;
  public endResultList!: Array<MapLocation>;


  constructor() {}

  ngOnInit(): void {}


  getSearchedLocation(isStart:boolean): void{
    let query = "";
    if(isStart)
      {
        query = this.searchedStartLocationInput.toString();
        fetch('https://nominatim.openstreetmap.org/search?format=json&polygon=1&addressdetails=1&q=' + query)
        .then(result => result.json())
        .then(parsedResult => {
            this.startResultList = parsedResult;
            console.log(this.startResultList);
        });
      }
    else
      {
        query = this.searchedEndLocationInput.toString();
        fetch('https://nominatim.openstreetmap.org/search?format=json&polygon=1&addressdetails=1&q=' + query)
        .then(result => result.json())
        .then(parsedResult => {
            this.endResultList = parsedResult;
            console.log(this.endResultList);
        });
      }
  }
  @Output() startLocationSelectedEvent = new EventEmitter<MapLocation>();
  startLiOnClick(res: MapLocation): void{
    this.selectedStartLocation = res;
    this.searchedStartLocationInput = res.display_name;
    this.startLocationSelectedEvent.emit(this.selectedStartLocation);
    this.startResultList = [];
  }

  @Output() endLocationSelectedEvent = new EventEmitter<MapLocation>();
  endLiOnClick(res: MapLocation): void{
    this.selectedEndLocation = res;
    this.searchedEndLocationInput = res.display_name;
    this.endLocationSelectedEvent.emit(this.selectedEndLocation);
    this.endResultList = [];
  }

  @Output() sendRequestForRideEvent = new EventEmitter<MapLocation>();
  sendLocationsToPage(): void{
    console.log("KLIKNUTO")
    this.sendRequestForRideEvent.emit(this.selectedStartLocation);
  }
}

