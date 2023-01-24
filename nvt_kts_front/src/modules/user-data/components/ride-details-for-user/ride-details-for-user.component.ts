import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ride-details-for-user',
  templateUrl: './ride-details-for-user.component.html',
  styleUrls: ['./ride-details-for-user.component.css']
})
export class RideDetailsForUserComponent implements OnInit {
  public showPanel: boolean = false;

  public name: string;
  public surname: string;
  public phoneNumber: string;
  public carType: string;
  public babyAllowed: boolean;
  public petAllowed: boolean;
  public startLocation: string;
  public endLocation: string;
  public betweenLocations: string;
  
  constructor() { 
    this.name = "";
    this.surname = "";
    this.phoneNumber = "";
    this.carType = "";
    this.babyAllowed = false;
    this.petAllowed = false;
    this.startLocation = "";
    this.endLocation = "";
    this.betweenLocations = "";
  }

  ngOnInit(): void {
  }

  setUpData(rowData: any): void {
    console.log(rowData);
    this.name = rowData.driver.name;
    this.surname = rowData.driver.surname;
    this.phoneNumber = rowData.driver.phone;
    this.carType = rowData.driver.carType;
    this.babyAllowed = rowData.driver.babyAllowed;
    this.petAllowed = rowData.driver.petAllowed;
    this.startLocation = rowData.ride.route.startLocation.locationName;
    this.endLocation = rowData.ride.route.endLocation.locationName;
    if (rowData.ride.route.optionalLocations.length !== 0)
      this.betweenLocations = rowData.ride.route.optionalLocations.join(', ');
    else 
    this.betweenLocations = "No between locations";

    this.showPanel = true;
  }

  goToNewRidePage(): void {

  }

  showDriverReviews(): void {

  }
  
}
