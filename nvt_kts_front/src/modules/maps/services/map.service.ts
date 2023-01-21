import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ride } from '../components/active-vehicle/Ride';
import { Driver } from '../components/active-vehicle/Driver';

@Injectable({
  providedIn: 'root'
})
export class MapService {

  constructor(private http: HttpClient) { }

  majmun() {
    this.http.get<String>("api/drivers/78").subscribe(
      {next: (response) => {console.log(response);
      }}
    )
  }

  getAllActiveDrivers(): Observable<Driver[]>{
    return this.http.get<Driver[]>("api/drivers/getDrivers")
  }


  getAllActiveRides(): Observable<Ride[]> {
    return this.http.get<Ride[]>("api/rides/getRides");
  }

  getDriverFromRide(driverID:string): Observable<Driver> {
    return this.http.get<Driver>("api/drivers/getDriver/"+driverID);
  }

}
