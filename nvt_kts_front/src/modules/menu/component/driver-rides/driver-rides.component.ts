import { ASTWithSource } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';

import * as L from 'leaflet';
import GeocoderControl, {Geocoder, geocoders} from 'leaflet-control-geocoder';
import { GeocodingCallback } from 'leaflet-control-geocoder/dist/geocoders';
import { RideForNotification } from 'src/modules/app/model/ride';
import { RideService } from 'src/modules/reports/services/ride.service';
import { UserDataService } from 'src/modules/user-data/services/user-data.service';


@Component({
  selector: 'app-driver-rides',
  templateUrl: './driver-rides.component.html',
  styleUrls: ['./driver-rides.component.css']
})
export class DriverRidesComponent implements OnInit {

  username: string = "zima@gmail.com";
  driverRides: RideForNotification[];

  constructor(
    private rideService: RideService,
    private userService: UserDataService
  ) { }

  ngOnInit(): void {
    this.setActiveStatus();
    this.rideService.findDriversUpcomingRides(this.username).subscribe((response) => {
      this.driverRides = <RideForNotification[]> response;
      this.addStringLocation();
      this.splitDate();

    });
  }

  setActiveStatus() {
    this.userService.getDrivesActiveStatus(this.username).subscribe((response) => {
      let b = <boolean> response;     
      let el: HTMLInputElement = document.querySelector("#flexSwitchCheckDefault")!;
      el.checked = b;
    });
    
  }

  changeStatus()
  {
    let el: HTMLInputElement = document.querySelector("#flexSwitchCheckDefault")!;
    let state: boolean =  el.checked
    this.userService.changeDriverActiveStatus(this.username, state);   
  }

  splitDate() {
    for(let ride of this.driverRides)
    {
      let datetime: string = ride.startDateTime;
      let lista:string[] = datetime.split("T");
      if(ride.state==="IN_PROGRESS" || ride.state==="RESERVED")
        ride.startDateTime = lista[1].slice(0, 5);
      else if (ride.state =='WAITING_FOR_PAYMENT' && ride.startDateTime!="2035") 
      {
        ride.startDateTime = lista[1].slice(0, 5) + " " + lista[0];
      }
      else
        ride.startDateTime = "TBD"
    }
  }

  report(id: number)
  {
    const e = document.getElementById("reportDiv-" + id);
    e?.classList.remove("reportDiv");
  }

  sendReport(id:number)
  {
    const e = document.getElementById("reportDiv-" + id);
    e?.classList.add("reportDiv");
    // nakon sto smo prijavili da nismo zapoceli voznju, treba tu voznju obrisati iz baze (i sa slike)
    this.setRideStatus(id, "DECLINED");
    this.rideService.changeRideState(id, "DECLINED");
    // TODO mozda poslati svima da je voznja odbijena
  }

  start(id:number)
  {
    this.setRideStatus(id, "IN_PROGRESS");
    this.rideService.changeRideState(id, "IN_PROGRESS");
  }

  end(id:number)
  {
    this.setRideStatus(id, "ENDED");
    this.rideService.changeRideState(id, "ENDED");
  }

  /*accept(id:number)
  {
    this.setRideStatus(id, "SCHEDULED");
    // ovdje treba voznji postaviti status u started ili tako nesot 
  }

  reject(id:number)
  {
    const e = document.getElementById("reasonDiv-" + id);
    e?.classList.remove("reasonDiv");

  }*/

  sendReason(id: number)
  {
    const e = document.getElementById("reasonDiv-" + id);
    e?.classList.add("reasonDiv");
    // nakon sto smo vozac odbije voznju, treba to nekako zapisati da se odbilo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    this.setRideStatus(id, "DECLINED");
    //this.rideService.changeRideState(id, "DECLINED");
  }


  setRideStatus(id: number, status: string) {
    for (let ride of this.driverRides)
    {
      if (ride.id==id)
      {
        ride.state = status;
        // treba jos i na back poslati da se status pormijenio  
        this.rideService.changeRideState(id, status);
        return;
      }
    }

  }

  addStringLocation() {
    let geocoder =new geocoders.Nominatim();
    let that = this;
    for(let ride of this.driverRides)
    {
      geocoder.geocode(ride.startLocation, function(results: any) {
        let k : string = results[0]["name"];
        let l: string [] = k.split(',');
        let rezultat: string = l[0] + "," + l[1] + "," + l[2]; 
        //let r: RideForNotification = that.driverRides.at(i)!;
        ride.startLocationString = rezultat;
        //that.driverRides.splice(i, 0, r);
          } 
      );
      geocoder.geocode(ride.endLocation, function(results: any) {
        let k : string = results[0]["name"];
        let l: string [] = k.split(',');
        let rezultat: string = l[0] + "," + l[1] + "," + l[2]; 
        ride.endLocationString = rezultat;
          } 
      );
    }
  }
}



