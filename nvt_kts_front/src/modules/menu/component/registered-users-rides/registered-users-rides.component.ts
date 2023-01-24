import { Component, OnInit } from '@angular/core';

import * as L from 'leaflet';
import GeocoderControl, {Geocoder, geocoders} from 'leaflet-control-geocoder';
import { GeocodingCallback } from 'leaflet-control-geocoder/dist/geocoders';
import { RideForNotification } from 'src/modules/app/model/ride';
import { RideService } from 'src/modules/reports/services/ride.service';


@Component({
  selector: 'app-registered-users-rides',
  templateUrl: './registered-users-rides.component.html',
  styleUrls: ['./registered-users-rides.component.css']
})
export class RegisteredUsersRidesComponent implements OnInit {


  username: string = "registrovani1@gmail.com";
  usersRides: RideForNotification[];


  constructor(
    
    private rideService: RideService,
  ) { }

  ngOnInit(): void {

    this.rideService.findUsersUpcomingRides(this.username).subscribe((response) => {
      this.usersRides = <RideForNotification[]> response;
      this.addStringLocation();
      this.splitDate();

    });
  }

  report(id: number)
  {
    const e = document.getElementById("reportDiv-" + id);
    e?.classList.remove("reportDiv");
  }

  view(id: number)
  {
    // TODO ovdje kod da ga preusmjerim kod Alekse
  }

  sendReport(id: number)
  {
    const e = document.getElementById("reportDiv-" + id);
    e?.classList.add("reportDiv");

    let btn : HTMLInputElement = (<HTMLInputElement>document.getElementById("reportButton-" + id));
    btn.innerHTML = "Driver reported";
    btn.setAttribute('disabled', '');

  }

  splitDate() {
    for(let ride of this.usersRides)
    {
      let datetime: string = ride.startDateTime;
      let lista:string[] = datetime.split("T");
      ride.startDateTime = lista[1].slice(0, 5);
    }
  }

  

  setRideStatus(id: number, status: string) {
    for (let ride of this.usersRides)
    {
      if (ride.id==id)
      {
        ride.state = status;
        this.rideService.changeRideState(id, status);
        return;
      }
    }

  }

  addStringLocation() {
    let geocoder =new geocoders.Nominatim();
    let that = this;
    for(let ride of this.usersRides)
    {
      geocoder.geocode(ride.startLocation, function(results: any) {
        let k : string = results[0]["name"];
        let l: string [] = k.split(',');
        let rezultat: string = l[0] + "," + l[1] + "," + l[2]; 
        ride.startLocationString = rezultat;
          } 
      );
    }
  }
}
