import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ChangePassword, ChangeProfileRequest, User } from 'src/modules/app/model/user';

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
  private getAllUsersURL: string;
  private getUserURL: string;
  private getDriverURL: string;

  private addNoteURL: string;
  private blockUserURL: string;
  private addTokensURL: string;
  private changeDriverActiveStatusURL: string;
  private getDrivesActiveStatusURL: string;
  private getAllRegisteredUsersMailsURL: string;

  private sendChangeRequestURL: string;
  private saveUserChangesURL: string;
  private sendChangePasswordRequestURL: string;
  private activateProfileURL: string;
  private sendRegistrationEmailURL: string;
  private checkIfExistsURL: string;



  //private usersUrl: string;

  constructor(private http: HttpClient) {
    this.getChangedProfilesUrl = 'api/changeProfileRequests/getChangedProfiles';
    this.saveChangesUrl = 'api/changeProfileRequests/saveChanges';
    this.declineChangesUrl = 'api/changeProfileRequests/declineChanges';
    this.addDriverUrl = 'api/drivers/addDriver';
    this.addUserUrl = 'api/registeredUsers/addUser';
    this.getAllUsersURL = "api/user/getAllUsers";

    this.getDriverURL = "api/drivers/getDriverData/";
    this.getUserURL = "api/registeredUsers/getUserData/";

    this.addNoteURL = "api/user/addNote";
    this.blockUserURL = "api/user/blockUser";
    this.addTokensURL = "api/registeredUsers/addTokens/";
    this.changeDriverActiveStatusURL = "api/drivers/changeDriverActiveStatus/";
    this.getDrivesActiveStatusURL = "api/drivers/getDrivesActiveStatus/";

    this.getAllRegisteredUsersMailsURL = "api/registeredUsers/getAllRegisteredUsersMails";
    this.sendChangeRequestURL = "api/changeProfileRequests/sendChangeRequest";

    this.saveUserChangesURL = "api/user/saveUserChanges";
    this.sendChangePasswordRequestURL = "api/user/sendChangePasswordRequest"

    this.activateProfileURL = "api/user/activateProfile/";
    this.sendRegistrationEmailURL = "api/mail/sendRegistrationEmail/";
    this.checkIfExistsURL = "api/user/checkIfExist/";

  }

  public dumbMethod()
  {
    return "Nevena";
  }

  checkIfAlreadyExists(email: string) {
    return this.http.get(this.checkIfExistsURL + email, {
      headers: this.headers,
      responseType: "json",
    })
  }

  activateProfile(email: string) {
    this.http.post(this.activateProfileURL + email, {
      headers: this.headers,
      responseType: "json",
    }).subscribe(() => {
    });
  }

  sendRegistrationEmail(email: string) {
    this.http.get(this.sendRegistrationEmailURL + email, {
      headers: this.headers,
      responseType: "json",
    }).subscribe(() => {
    });
  }

  getDrivesActiveStatus(username: string) {
    return this.http.get(this.getDrivesActiveStatusURL + username, {
      headers: this.headers,
      responseType: "json",
    })
  }

  changeDriverActiveStatus(email: string, active: boolean) {
    this.http.post(this.changeDriverActiveStatusURL + email + "/" + active, {
      headers: this.headers,
      responseType: "json",
    }).subscribe(() => {
    });
  }

  getChangedProfiles(): Observable<ChangeProfileRequest[] []> {
    return this.http.get<ChangeProfileRequest[][]>(this.getChangedProfilesUrl, {
      headers: this.headers,
      responseType: "json",
    });
  }

  blockUser(email: string, block: boolean) {
    this.http.get(this.blockUserURL + "/" + block + "/" + email, {
      headers: this.headers,
      responseType: "json",
    }).subscribe(() => {
    });
  }

  addNote(newNote: string, currentUser: string) {
    let fullURL = this.addNoteURL + "/" + newNote + "/" + currentUser;
    this.http.get(fullURL, {
      headers: this.headers,
      responseType: "json",
    }).subscribe(() => {
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
    let ret : User = {name:"", surname:"", email:"", city:"", phone:"", picture:"", note:"", blocked:false, tokens:0};
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

  getAllUserData(): Observable<User[]> {
    return this.http.get<User[]>(this.getAllUsersURL, {
      headers: this.headers,
      responseType: "json",
    });
  }

  getUserData(email: string): Observable<User> {
    return this.http.get<User>(this.getUserURL + email, {
      headers: this.headers,
      responseType: "json",
    });
  }

  getDriverData(email: string): Observable<ChangeProfileRequest> {
    return this.http.get<ChangeProfileRequest>(this.getDriverURL + email, {
      headers: this.headers,
      responseType: "json",
    });
  }


  addTokens(value: string, username: string) {
    return this.http.get<User>(this.addTokensURL + username + "/" + value, {
      headers: this.headers,
      responseType: "json",
    });
  }

  getAllRegisteredUsersMails() {
    return this.http.get(this.getAllRegisteredUsersMailsURL, {
      headers: this.headers,
      responseType: "json",
    })
  }
  getUsersStateBasedOnHisRides(userEmail:string): Observable<boolean>{
    return this.http.get<boolean>("api/registeredUsers/getUserStateBasedOnRide/"+userEmail)
  }

  sendChangeRequest(user: ChangeProfileRequest) {
    this.http.post(this.sendChangeRequestURL, user, {
      headers: this.headers,
      responseType: "json",
    }).subscribe(() => {
    });
  }

  saveUserChanges(user: ChangeProfileRequest) {
    this.http.post(this.saveUserChangesURL, user, {
      headers: this.headers,
      responseType: "json",
    }).subscribe(() => {
    });
  }

  saveChangedImage(uploadImageData: FormData,userEmail:string) {
    this.http.post('api/registeredUsers/imgUploadPROBA/'+userEmail, uploadImageData)
    .subscribe((response) => {

    });
  }

  saveChangedImageDriver(uploadImageData: FormData,userEmail:string) {
    this.http.post('api/drivers/imgUploadPROBA/'+userEmail, uploadImageData)
    .subscribe((response) => {

    });
  }


  sendChangePasswordRequest(pass: ChangePassword) {
    this.http.post(this.sendChangePasswordRequestURL, pass, {
      headers: this.headers,
      responseType: "json",
    }).subscribe(() => {
    });
  }

}
