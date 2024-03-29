import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthService } from '@abacritt/angularx-social-login';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { iif } from 'rxjs';
import { API_ALL_ACTIVE_VEHICLES_URL } from 'src/config/map-urls';
import { MenuService } from 'src/modules/menu/service/menu-service';
import { UserDataService } from 'src/modules/user-data/services/user-data.service';
import { FieldValidator } from 'src/utils/field-validator';
import { APIRequestMaker } from "../../../../utils/api-request-maker";


@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  @Output() errSignal = new EventEmitter<string>();

  public email: string;
  public password: string;

  private isLoginBtnClickable: boolean;


  constructor(public reqMaker: APIRequestMaker, private fieldvalidator: FieldValidator, 
              private menuService: MenuService, private authService: SocialAuthService,
              private userDataService: UserDataService,
              private router: Router) {
    this.email = "";
    this.password = "";

    this.isLoginBtnClickable = true;
  }

  ngOnInit(): void {
    this.authService.authState.subscribe((user) => {
      let data = {
        email: user.email,
        authToken: "",
        name: user.firstName,
        surname: user.lastName,
        picturePath: ""
      }

      let provider = user.provider;
      if(provider === "GOOGLE") {
        data.authToken = user.idToken;
        this.reqMaker.createGoogleLoginRequest(data).subscribe(this.getFacebookOrGoogleLoginObservable());
      }
      else if (provider === "FACEBOOK") {
        data.authToken = user.authToken;
        this.reqMaker.createFacebookLoginRequest(data).subscribe(this.getFacebookOrGoogleLoginObservable()); 
      }
    });
  }

  onLoginBtnClick(): void {
    if (!this.isLoginBtnClickable)
      return;

    this.isLoginBtnClickable = false;
    if (!this.validateLoginForm()) {
      this.isLoginBtnClickable = true;
      return;
    }

    let data = {
      email: this.email,
      password: this.password
    }
    this.reqMaker.createLoginRequest(data).subscribe(this.getSystemLoginObservable());
  }

  validateLoginForm(): boolean {
    if (this.fieldvalidator.isEmpty(this.email) || this.fieldvalidator.isEmpty(this.password))
      return this.emitError("Please make sure you filled all the fields before loging in.");

    if (!this.fieldvalidator.validateEmail(this.email))
      return this.emitError("Email address you entered is not valid.");
    
    return true;
  }

  emitError(errMessage: string): boolean {
    this.errSignal.emit(errMessage);
    return false;
  }

  getSystemLoginObservable() {
    return {
      next: (retData: any) => {
        if (retData.body === undefined)
          return;
        
        console.log("asfassafas");
        const tokenData : any = jwtDecode(retData.body.accessToken);
        localStorage.setItem('token', retData.body.accessToken);
        localStorage.setItem('email', tokenData['sub']);
        localStorage.setItem('role', retData.body.role);
      },

      error: (err: any) => {
        console.log(err);
        if (err.status === 401)
          this.emitError("Email or/and password you entered are not valid. Try again.");
        else if (err.status === 423)
          this.emitError("Your account has either been locked or is not activated yet.");
        else
          this.emitError("Something happened. Please try again later.");
        
          this.isLoginBtnClickable = true;
      },
      
      complete: () => { 
        
        if (localStorage.getItem('role')=="DRIVER")
        {
            this.userDataService.changeDriverActiveStatus(localStorage.getItem('email')!, true);
        }
        this.redirectAfterLogin(); }
    };
  }

  getFacebookOrGoogleLoginObservable() {
    return {
      next: (retData: any) => {
        if (retData.body === undefined)
          return;

        const tokenData : any = jwtDecode(retData.body.accessToken);
        localStorage.setItem('token', retData.body.accessToken);
        localStorage.setItem('email', tokenData['sub']);
        localStorage.setItem('role', retData.body.role);
      },

      error: (err: any) => {
        if (err.status === 423)
          this.emitError("Your account has either been locked or is not activated yet.");
        else
          this.emitError("Something happened. Please try again later.");
      },
      
      complete: () => { this.redirectAfterLogin(); }
    };
  }

  redirectAfterLogin(): void{
    this.menuService.updateMenu();
    this.router.navigate([API_ALL_ACTIVE_VEHICLES_URL]);
  }

  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }

  signInWithFB(): void {
    this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);
  }

  signOut(): void {
    this.authService.signOut();
  }
}
