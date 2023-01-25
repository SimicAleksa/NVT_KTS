import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-ride-details-for-user',
  templateUrl: './ride-details-for-user.component.html',
  styleUrls: ['./ride-details-for-user.component.css']
})
export class RideDetailsForUserComponent implements OnInit {
  @Output() showReviewsBtnClickedSignal = new EventEmitter<number>();

  public showPanel: boolean = false;

  public id: number;
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
    this.id = -1;
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
    this.id = rowData.driver.id;
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
    //TO DO
  }

  showDriverReviews(): void {
    this.showReviewsBtnClickedSignal.emit(this.id);
  }
  
}
