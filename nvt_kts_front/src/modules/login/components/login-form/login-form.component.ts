import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthService } from '@abacritt/angularx-social-login';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import jwtDecode from 'jwt-decode';
import { MenuService } from 'src/modules/menu/service/menu-service';
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


  constructor(private reqMaker: APIRequestMaker, private fieldvalidator: FieldValidator, 
              private menuService: MenuService, private authService: SocialAuthService) {
    this.email = "";
    this.password = "";

    this.isLoginBtnClickable = true;
  }

  ngOnInit(): void {
    this.authService.authState.subscribe((user) => {
      let data = {
        email: user.email,
        authToken: user.authToken,
        name: user.firstName,
        surname: user.lastName,
        picturePath: user.photoUrl
      }
      
      this.reqMaker.createFacebookLoginRequest(data).subscribe(this.getFBLoginObservable());
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
        const tokenData : any = jwtDecode(retData.body.accessToken)
        localStorage.setItem('token', retData.body.accessToken);
        localStorage.setItem('email', tokenData['sub']);
        localStorage.setItem('role', retData.body.role);
      },

      error: (err: any) => {
        if (err.status === 401)
          this.emitError("Email or/and password you entered are not valid. Try again.");
        else if (err.status === 423)
          this.emitError("Your account has either been locked or is not activated yet.");
        else
          this.emitError("Something happened. Please try again later.");
        
          this.isLoginBtnClickable = true;
      },
      
      complete: () => { this.redirectAfterLogin(); }
    };
  }

  getFBLoginObservable() {
    return {
      next: (retData: any) => {
        if (retData.body === undefined)
          return;
        localStorage.setItem('token', retData.body.accessToken);
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

  redirectAfterLogin() {
    this.menuService.updateMenu();
    //TODO router.navigate
    alert("Logged");
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
