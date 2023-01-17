import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ride } from '../components/active-vehicle/Ride';

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

  getAllActiveRides(): Observable<Ride[]> {
    return this.http.get<Ride[]>("api/rides/getRides");
  }

}
