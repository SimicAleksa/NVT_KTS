import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-ride-history-table',
  templateUrl: './ride-history-table.component.html',
  styleUrls: ['./ride-history-table.component.css']
})
export class RideHistoryTableComponent implements OnInit {
  @Output() rowClickedSignal = new EventEmitter<any>();
 
  @Input() rideHistory: Array<any>;
  
  public sortedColumn: string;
  public isSortDirAsc: boolean;


  constructor(private datePipe: DatePipe) { 
    this.rideHistory = new Array;
    this.sortedColumn = "startTime";
    this.isSortDirAsc = true;

    this.sortByStartTime();
  }

  ngOnInit(): void {}

  convertDateTime(dateTime: string) {
    return this.datePipe.transform(dateTime, 'medium');
  }

  sortColumn(colName: string): void {
    if (this.sortedColumn === colName)
      this.isSortDirAsc = !this.isSortDirAsc;
    else {
      this.sortedColumn = colName;
      this.isSortDirAsc = true;
    }
    switch(this.sortedColumn) {
      case "startLocation":
        this.sortByStartLocation();
        break;

      case "endLocation":
        this.sortByEndLocation();
        break;
        
      case "price":
        this.sortByPrice();
        break;

      case "startTime":
        this.sortByStartTime();
        break;

      case "endTime":
        this.sortByEndTime();
        break;
    }

  }

  sortByStartLocation(): void {
    this.rideHistory.sort((a, b) => {
      return this.compare(a.ride.route.startLocation.locationName, b.ride.route.startLocation.locationName);
    });
  }

  sortByEndLocation(): void {
    this.rideHistory.sort((a, b) => {
      return this.compare(a.ride.route.endLocation.locationName, b.ride.route.endLocation.locationName);
    });
  }

  sortByPrice(): void {
    this.rideHistory.sort((a, b) => {
      return this.compare(a.ride.price, b.ride.price);
    });
  }

  sortByStartTime(): void {
    this.rideHistory.sort((a, b) => {
      return this.compare(a.ride.startDateTime, b.ride.startDateTime);
    });
  }

  sortByEndTime(): void {
    this.rideHistory.sort((a, b) => {
      return this.compare(a.ride.endDateTime, b.ride.endDateTime);
    });
  }

  compare(thing1: any, thing2: any): number {
    let retVal = 0;
    if (thing1 < thing2)
      retVal = -1;
    else if (thing1 > thing2)
      retVal = 1;

    if (!this.isSortDirAsc)
      retVal *= -1;

    return retVal;
  }

  openRideDetails(rideHistoryData: any): void {
    this.rowClickedSignal.emit(rideHistoryData);
  }
}

