import { Component, OnInit } from '@angular/core';
import { MapLocation } from 'src/modules/app/model/mapLocation';

@Component({
  selector: 'app-search-routes-page',
  templateUrl: './search-routes-page.component.html',
  styleUrls: ['./search-routes-page.component.css']
})
export class SearchRoutesPageComponent implements OnInit {

  public selectedStartLocation!:MapLocation;
  public selectedEndLocation!:MapLocation;

  constructor() { }

  ngOnInit(): void {
  }

  recieveSentSelectedStartLocation(emitedValue: MapLocation){
    this.selectedStartLocation = emitedValue;
    console.log("hvata na stranici");
    console.log(emitedValue);
  }

  recieveSentSelectedEndLocation(emitedValue: MapLocation){
    this.selectedEndLocation = emitedValue;
    console.log("hvata na stranici");
    console.log(emitedValue);
  }

  recieveRequestForRide(emitedValue: MapLocation){
    // this.selectedLocation = emitedValue;
    console.log("hvata na stranici");
    console.log(emitedValue);
  }

}
