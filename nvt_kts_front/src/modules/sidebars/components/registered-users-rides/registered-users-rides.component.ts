import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import * as L from 'leaflet';
import GeocoderControl, {Geocoder, geocoders} from 'leaflet-control-geocoder';
import { GeocodingCallback } from 'leaflet-control-geocoder/dist/geocoders';
import { RideForNotification } from 'src/modules/app/model/ride';
import { RideService } from 'src/modules/reports/services/ride.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { StringDTO } from 'src/modules/app/model/stringDTO';
import { RideForDurationDTO } from 'src/modules/app/model/rideForDurationDTO';
import { RideDtoWithExpectedDuration } from 'src/modules/app/model/rideDTOWithExpectedDuration';
import { API_INCOMING_DRIVER, API_TRACKING_ROUTE } from 'src/config/map-urls';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-registered-users-rides',
  templateUrl: './registered-users-rides.component.html',
  styleUrls: ['./registered-users-rides.component.css']
})
export class RegisteredUsersRidesComponent implements OnInit {


  username: string = String(localStorage.getItem('email'));
  usersRides: RideForNotification[];
  usersDTSRIDE:RideDtoWithExpectedDuration;
  usersTimeRemainingTillGettingARide:string;

  private stompClient: any;
  public ws: any;


  constructor(

    private rideService: RideService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {

    this.toastr.success("PUFNA");
    this.initializeWebSocketConnection();
    this.rideService.findUsersUpcomingRides(this.username).subscribe((response) => {
      this.usersRides = <RideForNotification[]> response;
      this.addStringLocation();
      this.splitDate();
    });
  }

  alreadyApproved(ride: RideForNotification)
  {
    //alert(ride.approvedBy + " je lista za " + ride.id);
    let users = ride.approvedBy.split("$");
    if (users.includes(this.username))
    {
      return true;
    }
    return false;
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
      alert("SOCKET AKTIVIRAN")
      let rideNotif:RideForNotification = JSON.parse(message.body);
      if(rideNotif.passengerEmail===this.username){
        alert("SOCKET USAO")
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


    this.stompClient.subscribe('/map-updates/everyone-approved', (message: { body: string }) => {
      let object: StringDTO = JSON.parse(message.body);
      let id: number = object.numberValue;
      alert("SOCKET AKTIVIRAN jer su svi odobrili TODO: izbaciti obavjestenje");
      // sad znam da je voznja sa ID-em 2 odobrena od strane svih
      // ako ima saljem mu alert da je odobrena i na frontu prebacujem stanje u STARTED
      for (let ride of this.usersRides)
      {
        if (ride.id==id)
        {
          ride.state = "STARTED";
        }
      }

    });

    this.stompClient.subscribe('/map-updates/no-drivers-available', (message: { body: string }) => {
      let object: StringDTO = JSON.parse(message.body);
      let id: number = object.numberValue;
      alert("SOCKET AKTIVIRAN jer nema vozaca TODO: izbaciti obavjestenje");
      alert("Your ride is declined because there are no available drivers");
      // sad znam da je voznja sa ID-em 2 odobrena od strane svih
      // ako ima saljem mu alert da je odobrena i na frontu prebacujem stanje u STARTED
      for (let ride of this.usersRides)
      {
        if (ride.id==id)
        {
          ride.state = "DECLINED";
        }
      }

    });

    this.stompClient.subscribe('/map-updates/ride-status-changed', (message: { body: string }) => {
      let object: StringDTO = JSON.parse(message.body);
      let id: number = object.numberValue;
      let state: string = object.value;
      const map: { [id: string]: string; } = createRideStateDictionary();
      //const map = {};

      alert("SOCKET AKTIVIRAN jer se promijenilo stanje voznje");
      for (let ride of this.usersRides)
      {
        if (ride.id==id)
        {
          // ponovo cu da dobavim voznje
          this.rideService.findUsersUpcomingRides(this.username).subscribe((response) => {
            this.usersRides = <RideForNotification[]> response;
            this.addStringLocation();
            this.splitDate();
          });
          // i obavijesticu korisnika da mu se zavrsila voznja
          alert("Your ride " + map[state]);
        }
      }

    });

  }
  seeIncDriver(ride_id:number){
    this.router.navigate([API_INCOMING_DRIVER]);
  }

  rejectRide(id: number)
  {
    this.setRideStatus(id, "DECLINED");
    this.rideService.changeRideState(id, "DECLINED");
  }

  acceptRide(id: number)
  {

    this.rideService.acceptRideUser(id, this.username).subscribe((response) => {
      let dto: StringDTO = <StringDTO> response;
      if (dto.value=="OK")
      {
        this.setRideApprovedBy(id);
        alert("Odobrili ste voznju");
      }
      else if (dto.value="NO_TOKENS")
      {
        alert("Nema dovoljno tokena");
        // napisati na frontu da nema tokena TODO: javiti i ostalima da nema tokena
        this.rideService.changeRideState(id, "DECLINED");
        this.setRideStatus(id, "DECLINED");
      }
    });

  }

  setRideApprovedBy(id: number) {
    for (let ride of this.usersRides)
    {
      if (ride.id==id)
      {
        let newVal = ride.approvedBy + "$" + this.username;
        ride.approvedBy = newVal;
        return;
      }
    }
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
      else if (ride.state =='WAITING_FOR_PAYMENT' && ride.startDateTime!="2035")
      {
        ride.startDateTime = lista[1].slice(0, 5) + " " + lista[0];
      }
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


function createRideStateDictionary(): { [id: string]: string; } {
  const map: { [id: string]: string; } = {};
  map["STARTED"] = "is approved";
  map["ENDED"] = "is over";
  map["DECLINED"] = "is declined";
  map["RESERVED"] = "is reserved";
  map["IN_PROGRESS"] = "has started"
  return map;

}

