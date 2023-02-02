import { ComponentFixture, TestBed, tick } from '@angular/core/testing';
import { AbstractControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {HttpClientModule} from '@angular/common/http';

import { RegistrationFormComponent } from './registration-form.component';
import { ToastrModule } from 'ngx-toastr';
import { By } from '@angular/platform-browser';
import { UserDataService } from '../../services/user-data.service';
import { UserDataServiceMock } from '../../mocks/user-data-service.mock';

fdescribe('RegistrationFormComponent', () => {
  let component: RegistrationFormComponent;
  let fixture: ComponentFixture<RegistrationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormsModule,
        ReactiveFormsModule,
        HttpClientTestingModule,
        ToastrModule.forRoot(),],
      
      declarations: [ RegistrationFormComponent ],
      providers: [
        {provide: UserDataService, useClass: UserDataServiceMock}
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('register button should be present', () => {
    const data = fixture.nativeElement;
    expect(data.querySelector("#registerBtn").textContent).toContain("Register");
  });

  it("should disable the button when form is not valid", () => {
    fixture.detectChanges();
    const button = fixture.debugElement.query(By.css("button"));
    expect(button.nativeElement.disabled).toBeTruthy();
  });

  it("should enable button when form is valid", () => {
    component.registrationForm.get("name")?.setValue("Isidora");
    component.registrationForm.get("surname")?.setValue("Vasic");
    component.registrationForm.get("password")?.setValue("mladjaa");
    component.registrationForm.get("repeated")?.setValue("mladjaa");
    component.registrationForm.get("city")?.setValue("Novi Sad");
    fixture.detectChanges();
    const button = fixture.debugElement.query(By.css("#registerBtn"));
    expect(button.nativeElement.disabled).toBeTruthy();
    expect(component.registrationForm.valid).toBeFalsy();

  });


  it("should enable button when form is valid", () => {
    component.registrationForm.get("name")?.setValue("");
    component.registrationForm.get("surname")?.setValue("");
    component.registrationForm.get("email")?.setValue("");
    component.registrationForm.get("password")?.setValue("");
    component.registrationForm.get("repeated")?.setValue("");
    component.registrationForm.get("city")?.setValue("");
    component.registrationForm.get("phone")?.setValue("");

    component.registrationForm.get("name")?.setValue("Isidora");
    component.registrationForm.get("surname")?.setValue("Vasic");
    component.registrationForm.get("email")?.setValue("pera@gmail.com");
    component.registrationForm.get("password")?.setValue("mladjaa");
    component.registrationForm.get("repeated")?.setValue("mladjaa");
    component.registrationForm.get("city")?.setValue("Novi Sad");
    component.registrationForm.get("phone")?.setValue("065-881-98");
    fixture.detectChanges();
    const button = fixture.debugElement.query(By.css("#registerBtn"));
    expect(button.nativeElement.disabled).toBeFalsy();

  });

  /*it("should show message when form is invalid", async () => {

    component.registrationForm.get("name")?.setValue("");
    component.registrationForm.get("surname")?.setValue("");
    component.registrationForm.get("email")?.setValue("");
    component.registrationForm.get("password")?.setValue("");
    component.registrationForm.get("repeated")?.setValue("");
    component.registrationForm.get("city")?.setValue("");
    component.registrationForm.get("phone")?.setValue("");

    component.registrationForm.get("name")?.setValue("Isidora");
    component.registrationForm.get("surname")?.setValue("Vasic");
    component.registrationForm.get("email")?.setValue("pera@gmail.com");
    component.registrationForm.get("password")?.setValue("mladjaa");
    component.registrationForm.get("repeated")?.setValue("mladjaa");
    component.registrationForm.get("city")?.setValue("Novi Sad");
    component.registrationForm.get("phone")?.setValue("065-881-98");
    fixture.detectChanges();

    //const btn = fixture.debugElement.nativeElement.querySelector("#registerBtn");
    //btn.click();
    const submitEl = fixture.debugElement.query(By.css('#registerBtn'));
    submitEl.triggerEventHandler('click', null);
    fixture.detectChanges();

    const lbl = fixture.debugElement.nativeElement.querySelector("#alreadyExistLbl");
    alert(lbl.innerHTML);
    alert(lbl.innerHTML);
    alert(lbl.innerHTML);
    alert(lbl.innerHTML);
    alert(lbl.innerHTML);
    alert(lbl.innerHTML);

    expect(lbl.textContent).toContain("already exists");
    

  });*/

  /*it('service test', () => {
    const fakedBoolean = true;
    
    const userDataService = fixture.debugElement.injector.get(UserDataService);
    let spy = spyOn(userDataService, "checkIfAlreadyExists").and.returnValue(
      Promise.resolve(true)
    );
  });*/



  it('show negative message', () => {
    component.user = {name: "Pera", surname: "Peric", email:"pera@gmail.com", picture: "slika", city: "Novi Sad",
    phone: "065-654-44-65", note: "", blocked: false, tokens: 45, carType: "", babyAllowed: true, petsAllowed: true, password: "momo"};
    component.showNegativeMessage();

    const lbl = fixture.debugElement.nativeElement.querySelector("#alreadyExistLbl");
    expect(lbl.innerHTML).toContain("already exists");
    expect(lbl.innerHTML).toContain("pera@gmail.com");
  });



  it('save user', () => {
    component.user = {name: "Pera", surname: "Peric", email:"pera@gmail.com", picture: "slika", city: "Novi Sad",
    phone: "065-654-44-65", note: "", blocked: false, tokens: 45, carType: "", babyAllowed: true, petsAllowed: true, password: "momo"};
    component.saveUser();
    const lbl = fixture.debugElement.nativeElement.querySelector("#alreadyExistLbl");
    expect(lbl.innerHTML).toContain("confirmation email");
    expect(lbl.innerHTML).toContain("pera@gmail.com");
  });


  it('dumb method', () => {
    const userDataService = fixture.debugElement.injector.get(UserDataService);
    let ime: string = userDataService.dumbMethod();
    expect(ime).toContain("vena");
  });




});
