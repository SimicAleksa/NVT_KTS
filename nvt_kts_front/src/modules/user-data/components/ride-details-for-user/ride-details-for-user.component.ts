import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-ride-details-for-user',
  templateUrl: './ride-details-for-user.component.html',
  styleUrls: ['./ride-details-for-user.component.css']
})
export class RideDetailsForUserComponent implements OnInit {
  @Output() showReviewsBtnClickedSignal = new EventEmitter<number>();

  public showPanel: boolean = false;

  public details: any;

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
    this.details = null;
  }

  ngOnInit(): void {
  }

  setUpData(details: any): void {
    this.details = details;
    this.showPanel = true;
  }

  goToNewRidePage(): void {
    //TO DO
  }

  showDriverReviews(): void {
    this.showReviewsBtnClickedSignal.emit(this.details.driver.id);
  }
  
}
