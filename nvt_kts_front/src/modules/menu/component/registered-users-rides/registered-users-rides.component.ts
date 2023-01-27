import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as L from 'leaflet';
import GeocoderControl, {Geocoder, geocoders} from 'leaflet-control-geocoder';
import { GeocodingCallback } from 'leaflet-control-geocoder/dist/geocoders';
import { RideForNotification } from 'src/modules/app/model/ride';
import { RideService } from 'src/modules/reports/services/ride.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { RideForDurationDTO } from 'src/modules/app/model/rideForDurationDTO';
import { RideDtoWithExpectedDuration } from 'src/modules/app/model/rideDTOWithExpectedDuration';
import { API_INCOMING_DRIVER, API_TRACKING_ROUTE } from 'src/config/map-urls';


@Component({
  selector: 'app-registered-users-rides',
  templateUrl: './registered-users-rides.component.html',
  styleUrls: ['./registered-users-rides.component.css']
})
export class RegisteredUsersRidesComponent implements OnInit {


  username: string = "registrovani2@gmail.com";
  usersRides: RideForNotification[];
  usersDTSRIDE:RideDtoWithExpectedDuration;
  usersTimeRemainingTillGettingARide:string;
  
  private stompClient: any;
  public ws: any;


  constructor(
    
    private rideService: RideService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initializeWebSocketConnection();
    this.rideService.findUsersUpcomingRides(this.username).subscribe((response) => {
      this.usersRides = <RideForNotification[]> response;
      console.log(<RideForNotification[]> response);
      this.addStringLocation();
      this.splitDate();
    });
  }

  initializeWebSocketConnection() {
    this.ws = new SockJS('http://localhost:8000/socket');
    this.stompClient = Stomp.over(this.ws);
    this.stompClient.debug = null;
    let that = this;
    //alert(that.ws.readyState + " je stanje");
    this.stompClient.connect({}, function () {
      
      that.openGlobalSocket();
      //alert(that.ws.readyState + " je stanje");
    });
  }

  openGlobalSocket()
  {
    this.stompClient.subscribe('/map-updates/ride-notification', (message: { body: string }) => {
      console.log(message.body)
      let rideNotif:RideForNotification = JSON.parse(message.body);
      if(rideNotif.passengerEmail===this.username){
        this.rideService.findUsersUpcomingRides(this.username).subscribe((response) => {
          this.usersRides = <RideForNotification[]> response;
          this.addStringLocation();
          this.splitDate();
        });
      }
    });


    this.stompClient.subscribe('/map-updates/get-current-ride-duration', (message: { body: string }) => {
      let rideDurationride:RideForDurationDTO = JSON.parse(message.body);

      this.rideService.getUserDTSride(this.username).subscribe((response) => {
          if(response.id===rideDurationride.id){
            this.usersDTSRIDE = <RideDtoWithExpectedDuration> response;
            let jedanTempic:RideDtoWithExpectedDuration =  <RideDtoWithExpectedDuration> response;
            var minutes = Math.floor(jedanTempic.expectedDuration/60);
            var secunds = jedanTempic.expectedDuration - minutes*60;
            this.usersTimeRemainingTillGettingARide = minutes.toString()+"min "+secunds.toString().split('.')[0]+"sec";
          }
        });
        });
  }

  seeIncDriver(ride_id:number){
    this.router.navigate([API_INCOMING_DRIVER]);
  }


  report(id: number)
  {
    const e = document.getElementById("reportDiv-" + id);
    e?.classList.remove("reportDiv");
  }

  view(id: number)
  {
    this.router.navigate([API_TRACKING_ROUTE]);
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
      if(ride.state==="IN_PROGRESS" || ride.state==="RESERVED")
        ride.startDateTime = lista[1].slice(0, 5);
      else
        ride.startDateTime = "TBD"
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
