import { Component, OnInit,Output,EventEmitter } from '@angular/core';

@Component({
  selector: 'app-route-search-form',
  templateUrl: './route-search-form.component.html',
  styleUrls: ['./route-search-form.component.css'],
})
export class RouteSearchFormComponent implements OnInit {
  public searchedLocationInput!: String;
  public resultList!: any; // IZBACI ANY
  public selectedLocation!: any; // Isto izbaci any !! MORAS NAPRAVITI NEKI INTERVEJS


  constructor() {}

  ngOnInit(): void {}


  getSearchedLocation(): void{
    console.log("RODJACEEE--"+this.searchedLocationInput)
    const query = this.searchedLocationInput;
    fetch('https://nominatim.openstreetmap.org/search?format=json&polygon=1&addressdetails=1&q=' + query)
        .then(result => result.json())
        .then(parsedResult => {
            this.resultList = parsedResult;
            console.log(this.resultList);
        });
  }

  @Output() locationSelectedEvent = new EventEmitter<any>();
  liOnClick(res: any): void{
    this.selectedLocation = res;
    console.log(this.selectedLocation);
    this.locationSelectedEvent.emit(this.selectedLocation);
  }

}

