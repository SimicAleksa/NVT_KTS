import { Component, OnInit, Input, ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { UserDataService } from '../../services/user-data.service';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators, ReactiveFormsModule, FormControl } from '@angular/forms';
import { Router } from "@angular/router";
import { ChangeProfileRequest } from 'src/modules/app/model/user';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {
  registrationForm: FormGroup;

  user: ChangeProfileRequest;
  
  constructor(
    private fb: FormBuilder,
    private userDataService : UserDataService, 
    private toastr: ToastrService,
    ) { 
      this.createForm();
    }

  createForm() {
    this.registrationForm = this.fb.group({
      name: ["", [Validators.required, Validators.minLength(2)]],
      surname: ["", [Validators.required, Validators.minLength(2)]],
      email: ["", [Validators.required, Validators.minLength(3), this.validateEmail]],
      password: ["", [Validators.required, Validators.minLength(5)]],
      repeated: ["", [Validators.required, Validators.minLength(5)]],
      city: ["", [Validators.required, Validators.minLength(2)]],
      phone: ["", [Validators.required, Validators.minLength(6)]],
    }, {validator: this.passwordConfirming})
  }

  passwordConfirming(c: AbstractControl): { invalid: boolean} | null {
    //console.log("trenutno lozinka je " + c.get('password')?.value)
    if (c.get('password')?.value !== c.get('repeated')?.value) {
        return {invalid: true};
    }
    return null;
}

  validateEmail(control: AbstractControl): ValidationErrors | null {
    return control.value.includes("@") ? null : { emailError: true };
  }

 
  ngOnInit(): void {
  }

  onSubmit() {
    this.user = this.registrationForm.value;
    this.userDataService.addUser(this.user);
    this.userDataService.sendRegistrationEmail(this.user.email);

    //this.toastr.success("We've sent confirmation email on " + this.user.email);
    this.toastr.info("We've sent confirmation email on " + this.user.email, '', {
      positionClass: 'toast-bottom-right'
   });
  }
}



