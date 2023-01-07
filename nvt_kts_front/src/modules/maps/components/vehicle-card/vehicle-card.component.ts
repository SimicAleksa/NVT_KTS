import {Component, Input, OnInit} from '@angular/core';
import {IDriverDTO} from "../active-vehicle/driverDTO";
import {MapLocation} from "../../../app/model/mapLocation";
import {IDriverVehicleDTO} from "../active-vehicle/driverWithVehicleDTO";

@Component({
  selector: 'app-vehicle-card',
  templateUrl: './vehicle-card.component.html',
  styleUrls: ['./vehicle-card.component.css']
})
export class VehicleCardComponent implements OnInit {

  // @Input() vehicle!:IDriverDTO;
  @Input() vehicle!:IDriverVehicleDTO;

  constructor() { }

  ngOnInit(): void {
  }
}
