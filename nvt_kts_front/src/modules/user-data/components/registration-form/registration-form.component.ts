import { Component, OnInit, Input, ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { UserDataService } from '../../services/user-data.service';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {

  public name: string = "";
  public surname: string = "";
  public email: string = "";
  public password: string = "";
  public repeatedPassword: string = "";
  public city: string = "";
  public phone: string = "";

  @ViewChild('inputName') inputName!: ElementRef;
  @ViewChild('inputSurname') inputSurname!: ElementRef;
  @ViewChild('inputEmail') inputEmail!: ElementRef;
  @ViewChild('inputPassword') inputPassword!: ElementRef;
  @ViewChild('inputRepeated') inputRepeated!: ElementRef;
  @ViewChild('inputCity') inputCity!: ElementRef;
  @ViewChild('inputPhone') inputPhone!: ElementRef;


  @ViewChild('errorName') errorName!: ElementRef;
  @ViewChild('errorSurname') errorSurname!: ElementRef;
  @ViewChild('errorEmail') errorEmail!: ElementRef;
  @ViewChild('errorPassword') errorPassword!: ElementRef;
  @ViewChild('errorRepeated') errorRepeated!: ElementRef;
  @ViewChild('errorCity') errorCity!: ElementRef;
  @ViewChild('errorPhone') errorPhone!: ElementRef;

  constructor(private userDataService : UserDataService, private renderer: Renderer2) { }

  ngOnInit(): void {
  }


  register():void{
    this.clearPreviousMessages();
    if (this.allFieldAreProperlyFilled())
    {
      this.userDataService.register()
    }
    else{
      this.writeValidateMessage();
    }
      
  }
  clearPreviousMessages() {
    this.renderer.setStyle(this.errorName.nativeElement, "visibility", "collapse");
    this.renderer.setStyle(this.errorSurname.nativeElement, "visibility", "collapse");
    this.renderer.setStyle(this.errorEmail.nativeElement, "visibility", "collapse");
    this.renderer.setStyle(this.errorPassword.nativeElement, "visibility", "collapse");
    this.renderer.setStyle(this.errorRepeated.nativeElement, "visibility", "collapse");
    this.renderer.setStyle(this.errorCity.nativeElement, "visibility", "collapse");
    this.renderer.setStyle(this.errorPhone.nativeElement, "visibility", "collapse");

  }

  writeValidateMessage() {
    if (this.inputName.nativeElement.value==="")
    {
      this.renderer.setStyle(this.errorName.nativeElement, "visibility", "visible");
    }
    if (this.inputSurname.nativeElement.value==="")
    {
      this.renderer.setStyle(this.errorSurname.nativeElement, "visibility", "visible");
    }
    if (this.inputEmail.nativeElement.value.includes("@"))
     
    {
      this.renderer.setStyle(this.errorEmail.nativeElement, "visibility", "visible");
    }
    if (this.inputPassword.nativeElement.value==="")
    {
      this.renderer.setStyle(this.errorPassword.nativeElement, "visibility", "visible");
    }
    if (this.inputRepeated.nativeElement.value!=this.inputPassword.nativeElement.value)
    {
      this.renderer.setStyle(this.errorRepeated.nativeElement, "visibility", "visible");
    }
    if (this.inputCity.nativeElement.value==="")
    {
      this.renderer.setStyle(this.errorCity.nativeElement, "visibility", "visible");
    }
    if (this.inputPhone.nativeElement.value==="")
    {
      this.renderer.setStyle(this.errorPhone.nativeElement, "visibility", "visible");
    }
  }

  allFieldAreProperlyFilled()
  {
    return this.inputName.nativeElement.value!="" && this.inputSurname.nativeElement.value!="" && 
    this.inputPassword.nativeElement.value!="" && this.inputCity.nativeElement.value!="" && 
    this.inputPhone.nativeElement.value!="" && this.inputPassword.nativeElement.value===this.inputRepeated.nativeElement.value
     && this.inputEmail.nativeElement.value.includes("@");
  }
}

