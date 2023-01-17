import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ChangeProfileRequest, User } from 'src/modules/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {
  
  
  private headers = new HttpHeaders({ "Content-Type": "application/json"});
  private getChangedProfilesUrl: string;
  private saveChangesUrl : string;
  private declineChangesUrl : string;
  private addDriverUrl: string;
  private addUserUrl: string;



  //private usersUrl: string;

  constructor(private http: HttpClient) { 
    this.getChangedProfilesUrl = 'http://localhost:8000/getChangedProfiles';
    this.saveChangesUrl = 'http://localhost:8000/saveChanges';
    this.declineChangesUrl = 'http://localhost:8000/declineChanges';
    this.addDriverUrl = 'http://localhost:8000/driver/addDriver';
    this.addUserUrl = 'http://localhost:8000/users/addUser';
  }

  getChangedProfiles(): Observable<ChangeProfileRequest[] []> {
    return this.http.get<ChangeProfileRequest[][]>(this.getChangedProfilesUrl, {
      headers: this.headers,
      responseType: "json",
    });
  }

  saveChanges(email: string , requests: ChangeProfileRequest[][]) {
    let u : User = this.findCertainUser(email, requests);
    this.http.post(this.saveChangesUrl, u, {
      headers: this.headers,
      responseType: "json",      
    }).subscribe(() => {
    });
  }

  declineChanges(email: string , requests: ChangeProfileRequest[][]) {
    let u : User = this.findCertainUser(email, requests);
    this.http.post(this.declineChangesUrl, u, {
      headers: this.headers,
      responseType: "json",      
    }).subscribe(() => {
    });

  }

  findCertainUser(email: string, users: User[][]): User{
    let ret : User = {name:"", surname:"", email:"", city:"", phone:"", picture:""};
    for (let u of users)
    {
      if (u.at(1)?.email == email)
      {
        ret = u.at(1)!;
      }
    }
    return ret;
  }

  addDriver(driver: ChangeProfileRequest) {
    this.http.post(this.addDriverUrl, driver, {
      headers: this.headers,
      responseType: "json",      
    }).subscribe(() => {
    });
  }

  addUser(user: ChangeProfileRequest) {
    this.http.post(this.addUserUrl, user, {
      headers: this.headers,
      responseType: "json",      
    }).subscribe(() => {
    });
  }


}
