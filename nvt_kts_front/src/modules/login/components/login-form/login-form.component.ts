import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MenuService } from 'src/modules/menu/service/menu-service';
import { FieldValidator } from 'src/utils/FieldValidator';
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


  constructor(private reqMaker: APIRequestMaker, private fieldvalidator: FieldValidator, private menuService: MenuService) {
    this.email = "";
    this.password = "";

    this.isLoginBtnClickable = true;
  }

  ngOnInit(): void {}

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

    this.reqMaker.createLoginRequest(data).subscribe(this.getObservable());
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

  getObservable() {
    return {
      next: (retData: any) => {
        if (retData.body === undefined)
          return;
        localStorage.setItem('token', retData.body.accessToken);
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
      
      complete: () => {
        this.menuService.updateMenu();
        alert("Logged");
      }
    };
  }
}
