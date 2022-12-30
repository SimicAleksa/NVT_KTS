import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FieldValidator } from 'src/utils/FieldValidator';
import { APIRequestMaker } from "../../../../utils/api-request-maker";

@Component({
  selector: 'app-reset-password-form',
  templateUrl: './reset-password-form.component.html',
  styleUrls: ['./reset-password-form.component.css']
})
export class ResetPasswordFormComponent implements OnInit {
  @Output() errSignal = new EventEmitter<string>();
  @Output() succSignal = new EventEmitter<string>();

  public password: string;
  public confirmPassword: string;
  public code: string;
  private email: string;

  private isConfirmBtnClickable: boolean;

  constructor(private reqMaker: APIRequestMaker, private fieldvalidator: FieldValidator, private activatedRoute: ActivatedRoute) { 
    this.password = '';
    this.confirmPassword = '';
    this.code = '';
    this.email = this.getEmailFromUrl();

    this.isConfirmBtnClickable = true;
  }

  ngOnInit(): void {}

  onConfirmBtnClick() {
    if (!this.isConfirmBtnClickable)
      return;

    if (!this.validateLoginForm()) {
      this.isConfirmBtnClickable = true;
      return;
    }

    let data = {
      email: this.email,
      password: this.password,
      tempCode: this.code
    }
    
    this.reqMaker.createPasswordResetRequest(data).subscribe(this.getObservable());
  }

  validateLoginForm(): boolean {
    if (this.fieldvalidator.isEmpty(this.password) || this.fieldvalidator.isEmpty(this.confirmPassword) || this.fieldvalidator.isEmpty(this.code))
      return this.emitError("Please make sure you filled all the fields before clicking confirm button.");

    if (!this.fieldvalidator.validateEmail(this.email))
      return this.emitError("Make sure you used the link from you email address because provided email address is not valid.");

    if (this.password !== this.confirmPassword)
      return this.emitError("Make sure you entered the same password twice.");
    
    if(!this.fieldvalidator.isLenGraterThan(this.password, 7))
      return this.emitError("Password must be at least 7 characters long.");

    if (!this.fieldvalidator.isOfExactLength(this.code, 4))
      return this.emitError("Code you entered is not valid. Please make sure you entered the code sent via email");

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
          this.emitError("Code you entered is not valid. Please make sure you entered the code sent via email");
        else
          this.emitError("Something happened. Please try again later.");

        this.isConfirmBtnClickable = true;
      },
      
      complete: () => {
        this.emitSuccess("Password has been reset successfully. You can navigate to login page now and log in our platform.");
        this.password = '';
        this.confirmPassword = '';
        this.code = '';
      }
    };
  }

  getEmailFromUrl(): string {
    let email: string = '';

    this.activatedRoute.paramMap.subscribe(paramMap => {
      email = paramMap.get('email') || '';
    });

    return email;
  }
}
