import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReportParam } from 'src/modules/app/model/reportParams';

@Injectable({
  providedIn: 'root'
})
export class RideService {

  private headers = new HttpHeaders({ "Content-Type": "application/json"});
  private driverReportDataUrl: string;
  private userReportDataUrl: string;
  private adminReportDataUrl: string;


  constructor(private http: HttpClient) { 
    //this.driverReportDataUrl = 'http://localhost:8000/driver/getDriverReportData';
    this.driverReportDataUrl = "api/rides/getDriverReportData";
    this.userReportDataUrl = "api/rides/getUserReportData";
    this.adminReportDataUrl = "api/rides/getAdminReportData";
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


}
