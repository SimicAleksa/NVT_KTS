import { Component, OnInit } from '@angular/core';
import {IDriverDTO} from "./driverDTO";
import {IDriverVehicleDTO} from "./driverWithVehicleDTO";

@Component({
  selector: 'app-active-vehicle',
  templateUrl: './active-vehicle.component.html',
  styleUrls: ['./active-vehicle.component.css']
})
export class ActiveVehicleComponent implements OnInit {

  public vehicles: IDriverVehicleDTO[] = [
    {
      driverId: 1,
      carType: "Cupe",
      babyAllowed: false,
      petAllowed:  false,
      isDriverFree: true,
      active:  true,
      vehicleCoordsLat: 0,
      vehicleCoordsLen: 0
    },
    {
      driverId: 2,
      carType: "SUV",
      babyAllowed: false,
      petAllowed:  false,
      isDriverFree: true,
      active:  true,
      vehicleCoordsLat: 0,
      vehicleCoordsLen: 0
    }
  ];

  constructor() { }

  ngOnInit(): void {

  }

}
