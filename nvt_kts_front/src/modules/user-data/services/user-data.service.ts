import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  private usersUrl: string;
  //private apiUrl = '/user/addUser';
  //private headers = new HttpHeaders({ "Content-Type": "application/json"});

  constructor(private http: HttpClient) { 
    this.usersUrl = 'http://localhost:8000/neuspjeh';
  }


  register() {
    console.log("Ova funkcija dodaje na back novog korisnika");
    // this.http.get<any>(this.usersUrl).subscribe(data => console.log(data));
    this.http.get(this.usersUrl).subscribe(data => console.log(data));
    console.log("Izvrseno je");
  }
}
