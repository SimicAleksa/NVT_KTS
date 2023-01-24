import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReportParam } from 'src/modules/app/model/reportParams';
import { RideForNotification } from 'src/modules/app/model/ride';

@Injectable({
  providedIn: 'root'
})
export class RideService {



  private headers = new HttpHeaders({ "Content-Type": "application/json"});
  private driverReportDataUrl: string;
  private userReportDataUrl: string;
  private adminReportDataUrl: string;
  private driverNotificationRidesURL: string;
  private userNotificationRidesURL: string;
  private changeRideStateURL: string;


  constructor(private http: HttpClient) { 
    //this.driverReportDataUrl = 'http://localhost:8000/driver/getDriverReportData';
    this.driverReportDataUrl = "api/rides/getDriverReportData";
    this.userReportDataUrl = "api/rides/getUserReportData";
    this.adminReportDataUrl = "api/rides/getAdminReportData";
    this.driverNotificationRidesURL = "api/rides/getDriverNotificationRides/";
    this.userNotificationRidesURL = "api/rides/getUserNotificationRides/";
    this.changeRideStateURL = "api/rides/changeRideState/";
  }


  getDriverDataForReport(params: ReportParam): Observable<Map<string, Map<string, number>>> {
    return this.http.post<Map<string, Map<string, number>>>(this.driverReportDataUrl, params, {
      headers: this.headers,
      responseType: "json",      
    });
  }

  getUserDataForReport(params: ReportParam): Observable<Map<string, Map<string, number>>> {
    return this.http.post<Map<string, Map<string, number>>>(this.userReportDataUrl, params, {
      headers: this.headers,
      responseType: "json",      
    });
  }

  getAdminDataForReport(params: ReportParam): Observable<Map<string, Map<string, number>>> {
    return this.http.post<Map<string, Map<string, number>>>(this.adminReportDataUrl, params, {
      headers: this.headers,
      responseType: "json",      
    });
  }

  findDriversUpcomingRides(username: string): Observable<RideForNotification[]> {
    return this.http.get<RideForNotification[] >(this.driverNotificationRidesURL + username, {
      headers: this.headers,
      responseType: "json",
    });
  }

  findUsersUpcomingRides(username: string): Observable<RideForNotification[]> {
    return this.http.get<RideForNotification[] >(this.userNotificationRidesURL + username, {
      headers: this.headers,
      responseType: "json",
    });
  
  }

  changeRideState(id: number, state: string) {
    this.http.get(this.changeRideStateURL + id + "/"+ state, {
      headers: this.headers,
      responseType: "json",      
    }).subscribe(() => {
    });
  }
}
