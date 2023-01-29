import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserDataService } from '../../services/user-data.service';
import { ChangePassword, ChangeProfileRequest, User } from 'src/modules/app/model/user';
import { Router } from '@angular/router';


@Component({
  selector: 'app-edit-profile-driver',
  templateUrl: './edit-profile-driver.component.html',
  styleUrls: ['./edit-profile-driver.component.css']
})
export class EditProfileDriverComponent implements OnInit {

  carTypes: string[] = ["SUV", "HATCHBACK", "COUPE", "MINIVAN", "SEDAN", "VAN", "LIMOUSINE"];

  editForm: FormGroup;
  modalForm: FormGroup;
  username: string;
  driverData: ChangeProfileRequest;

  retrievedImage: any;
  selectedFile: File;
  isNewPictureSelected:boolean=false;


  constructor(
    private fb: FormBuilder,
    private userDataService: UserDataService,
    private router: Router,
    ) {
    this.username = "pera@gmail.com";
    this.createForm();
    this.createModalForm();
   }


   displayStyle = "none";
  
  openPopup() {
    this.displayStyle = "block";
  }
  closePopup() {
    this.displayStyle = "none";
  }

 
   createForm() {
    this.editForm = this.fb.group({
      name: ["", [Validators.required, Validators.minLength(2)]],
      surname: ["", [Validators.required, Validators.minLength(2)]],
      email: ["", [Validators.required, Validators.minLength(2)]],
      password: ["", [Validators.required, Validators.minLength(2)]],
      city: ["", [Validators.required, Validators.minLength(2)]],
      phone: ["", [Validators.required, Validators.minLength(2)]],
      carType: ["", [Validators.required]],
      babyAllowed: [false],
      petsAllowed: [false],
    })
  }

  createModalForm() {
    this.modalForm = this.fb.group({
      editPassword: ["", [Validators.required]],
      repeated: ["", [Validators.required]],
    }, {validator: this.passwordConfirming});

    this.setInitialValues();

  }

  passwordConfirming(c: AbstractControl): { invalid: boolean} | null {
    if (c.get('editPassword')?.value !== c.get('repeated')?.value) {
        return {invalid: true};
    }
    return null;
}

  ngOnInit(): void {
    this.editForm.controls['name'].disable();
    this.editForm.controls['surname'].disable();
    this.editForm.controls['email'].disable();
    this.editForm.controls['password'].disable();
    this.editForm.controls['city'].disable();
    this.editForm.controls['phone'].disable();
  }

  onFileChanged(event:any){
    this.selectedFile = event.target.files[0];
    this.isNewPictureSelected=true;
  }

  onChangePicture(){
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
    this.userDataService.saveChangedImageDriver(uploadImageData,this.username);
  }



  nameChanged()
  {
    this.editForm.controls['name'].enable();
  }

  surnameChanged()
  {
    this.editForm.controls['surname'].enable();
  }

  emailChanged()
  {
    this.editForm.controls['email'].enable();
  }

 
  cityChanged()
  {
    this.editForm.controls['city'].enable();
  }


  phoneChanged()
  {
    this.editForm.controls['phone'].enable();
  }

  onSubmit()
  {
    this.changePasswordIfEntered();
    this.changeEnteredData(); 
    if(this.isNewPictureSelected){
      this.onChangePicture()
    }
    window.location.reload();
  }

  changeEnteredData() {
    let name: string = this.editForm.get("name")?.value ? this.editForm.get("name")?.value :  this.driverData.name;
    let surname: string = this.editForm.get("surname")?.value ? this.editForm.get("surname")?.value :  this.driverData.surname;
    let city: string = this.editForm.get("city")?.value ? this.editForm.get("city")?.value :  this.driverData.city;
    let phone: string = this.editForm.get("phone")?.value ? this.editForm.get("phone")?.value :  this.driverData.phone;
    let carType: string = this.editForm.get("carType")?.value;
    let babyAllowed: boolean = this.editForm.get("babyAllowed")?.value;
    let petsAllowed: boolean = this.editForm.get("petsAllowed")?.value;

   
    let c: ChangeProfileRequest = {name: name, surname: surname, email: this.username, picture: "", city: city, phone: phone, 
            carType: carType, petsAllowed: petsAllowed, babyAllowed: babyAllowed, note:"", tokens: 0, blocked: false, password:""};
    this.userDataService.sendChangeRequest(c);

  }

  changePasswordIfEntered()
  {
    if (this.modalForm.get("editPassword")!.value!="")
    {
      let l: ChangePassword = {password: this.modalForm.get("editPassword")!.value, username: this.username}
      this.userDataService.sendChangePasswordRequest(l);
    }
  }

  
  onModalSubmit()
  {
    this.displayStyle = "none";
    //zatvorila sam modal
    // treba jos tu vrijednost da upisem u input polje za sifru
    this.editForm.get("password")?.setValue(this.modalForm.get("editPassword")?.value);
  }

  setInitialValues() {
    //let u: User = this.userDataService.getUserData(this.username);
    this.userDataService.getDriverData(this.username).subscribe((response) => {
      this.driverData = response;      
      this.editForm.get("name")?.setValue(this.driverData.name);
      this.editForm.get("surname")?.setValue(this.driverData.surname);
      this.editForm.get("email")?.setValue(this.driverData.email);
      this.editForm.get("password")?.setValue("lozinka");
      this.editForm.get("city")?.setValue(this.driverData.city);
      this.editForm.get("phone")?.setValue(this.driverData.phone);
      this.editForm.get("carType")?.setValue(this.driverData.carType);
      this.editForm.get("babyAllowed")?.setValue(this.driverData.babyAllowed);
      this.editForm.get("petsAllowed")?.setValue(this.driverData.petsAllowed);

      console.log(response)
      var retrieveResonse = response;
      var base64Data = retrieveResonse.picture;
      this.retrievedImage = 'data:image/jpeg;base64,' + base64Data;


    });
    
    
  }

}
