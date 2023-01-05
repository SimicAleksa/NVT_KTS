import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { FieldValidator } from 'src/utils/field-validator';
import { APIRequestMaker } from "../../../../utils/api-request-maker";


@Component({
  selector: 'app-forgotten-password-form',
  templateUrl: './forgotten-password-form.component.html',
  styleUrls: ['./forgotten-password-form.component.css']
})
export class ForgottenPasswordFormComponent implements OnInit {
  @Output() errSignal = new EventEmitter<string>();
  @Output() succSignal = new EventEmitter<string>();
  
  public email: string;

  private isSendMailBtnIsClickable: boolean;

  constructor(private reqMaker: APIRequestMaker, private fieldvalidator: FieldValidator, private router: Router) {
    this.email = '';

    this.isSendMailBtnIsClickable = true;
  }

  ngOnInit(): void {}

  onSendMailBtnClick() {
    if (!this.isSendMailBtnIsClickable)
      return;

    if (!this.validateLoginForm()) {
      this.isSendMailBtnIsClickable = true;
      return;
    }
    
    this.reqMaker.createForgottenPasswordRequest(this.email).subscribe(this.getObservable());
  }

  validateLoginForm(): boolean {
    if (this.fieldvalidator.isEmpty(this.email))
      return this.emitError("Please make sure you filled all the fields before loging in.");

    if (!this.fieldvalidator.validateEmail(this.email))
      return this.emitError("Email address you entered is not valid.");
    
    return true;
  }

  emitError(errMessage: string): boolean {
    this.errSignal.emit(errMessage);
    return false;
  }

  emitSuccess(succMessage: string) {
    this.succSignal.emit(succMessage);
  }

  getObservable() {
    return {
      error: (err: any) => {
        if (err.status === 401)
          this.emitError("There is no account with given email address.");
        else
          this.emitError("Something happened. Please try again later.");

        this.isSendMailBtnIsClickable = true;
      },
      
      complete: () => {
        this.emitSuccess("Password reset email has been sent to '" + this.email + "'. Please click the sent link in the mail to procceed.");
        this.email = '';
      }
    };
  }

}
