import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ChangeProfileRequest } from 'src/modules/app/model/user';
import { UserDataService } from '../../services/user-data.service';

@Component({
  selector: 'app-add-car',
  templateUrl: './add-car.component.html',
  styleUrls: ['./add-car.component.css']
})
export class AddCarComponent implements OnInit {
  carTypes: string[] = ["SUV", "HATCHBACK", "COUPE", "MINIVAN", "SEDAN", "VAN", "LIMOUSINE"];
  driver: ChangeProfileRequest;
  

  registrationForm: FormGroup;

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
      carType: ["", [Validators.required]],
      babyAllowed: [false],
      petsAllowed: [false],
    }, {validator: this.passwordConfirming},
    )     

  }

  validateEmail(control: AbstractControl): ValidationErrors | null {
    return control.value.includes("@") ? null : { emailError: true };
  }

  passwordConfirming(c: AbstractControl): { invalid: boolean} | null {
    //console.log("trenutno lozinka je " + c.get('password')?.value)
    if (c.get('password')?.value !== c.get('repeated')?.value) {
        return {invalid: true};
    }
    return null;
}

  ngOnInit(): void {
  }

  onSubmit() {
    /*this.wine = new Wine(this.wineForm.value);
    this.wineService.add(this.wine);
    this.wineForm.reset();
    this.router.navigate(["wines"]);*/
    this.driver = this.registrationForm.value;
    this.userDataService.addDriver(this.driver);
    this.userDataService.sendRegistrationEmail(this.driver.email);
    this.toastr.success("We've send confirmation email to " + this.driver.email);
  }

 
}
