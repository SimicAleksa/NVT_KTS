import { Component, OnInit } from '@angular/core';
import {IDriverDTO} from "./driverDTO";

@Component({
  selector: 'app-active-vehicle',
  templateUrl: './active-vehicle.component.html',
  styleUrls: ['./active-vehicle.component.css']
})
export class ActiveVehicleComponent implements OnInit {

  public vehicles: IDriverDTO[] = [
    {
      driverId: 1,
      carType: "Cupe",
      babyAllowed: false,
      petAllowed:  false,
      isDriverFree: true,
      active:  true
    },
    {
      driverId: 2,
      carType: "SUV",
      babyAllowed: false,
      petAllowed:  false,
      isDriverFree: true,
      active:  true
    }
  ];

  constructor() { }

  ngOnInit(): void {

  }

}
