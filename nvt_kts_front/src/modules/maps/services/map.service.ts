import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ride } from '../components/active-vehicle/Ride';
import { Driver } from '../components/active-vehicle/Driver';
import { DataForRideForBack } from '../components/active-vehicle/DataForRideForBACK';
import { Route } from '../components/active-vehicle/Route';

@Injectable({
  providedIn: 'root'
})
export class MapService {

  private headers = new HttpHeaders({ "Content-Type": "application/json"});
  constructor(private http: HttpClient) { }

  getUsersFavoriteRouteWithId(routeId:string):Observable<Route>{
    return this.http.get<Route>("api/rides/getUsersFavoriteRouteWithId/"+routeId);
  }

  getUsersDrivingTOStartEmail(username:string):Observable<Ride>{
    return this.http.get<Ride>("api/rides/getUserDTSride/"+username)
  }

  getUsersInProgressRideEmail(username:string):Observable<Ride>{
    return this.http.get<Ride>("api/rides/getUserInProgressRide/"+username)
  }


  getAllActiveDrivers(): Observable<Driver[]>{
    return this.http.get<Driver[]>("api/drivers/getDrivers")
  }

  getPassangersDrivingToStartRide(driverID:string): Observable<Ride> {
    return this.http.get<Ride>("api/rides/getDriversDTSRide/"+driverID);
  }


  getPassangersDrivingTheRouteRide(driverID:string): Observable<Ride> {
    return this.http.get<Ride>("api/rides/getDriversINPROGRESSRide/"+driverID);
  }


  getAllActiveRides(): Observable<Ride[]> {
    return this.http.get<Ride[]>("api/rides/getRides");
  }

  getDriverFromRide(driverID:string): Observable<Driver> {
    return this.http.get<Driver>("api/drivers/getDriver/"+driverID);
  }

  saveRide(rideData: DataForRideForBack) {
    this.http.post("api/rides/createRideFromFront", rideData, {
      headers: this.headers,
      responseType: "json",      
    }).subscribe(() => {
    });
  }

}
