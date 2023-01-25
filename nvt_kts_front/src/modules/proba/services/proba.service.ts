import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProbaService {

  
  private headers = new HttpHeaders({ "Content-Type": "application/json"});
  private getMinutesURL: string;

  constructor(private http: HttpClient) { 
    this.getMinutesURL = "api/drivers/getActiveMinutes/";

  }

  getMinutes(username: string) {
    return this.http.get<number>(this.getMinutesURL + username, {
      headers: this.headers,
      responseType: "json",
    });
  }
}
