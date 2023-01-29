import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserDataService } from '../../services/user-data.service';
import { ChangePassword, ChangeProfileRequest, User } from 'src/modules/app/model/user';
import { Router } from '@angular/router';



@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  editForm: FormGroup;
  modalForm: FormGroup;
  username: string;
  userData: User;
  retrievedImage: any;
  selectedFile: File;
  isNewPictureSelected:boolean=false;


  constructor(
    private fb: FormBuilder,
    private userDataService: UserDataService,
    private router: Router,
    ) {
    this.username = String(localStorage.getItem('email'));
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
    this.userDataService.saveChangedImage(uploadImageData,this.username);
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

  addMoney()
  {
    this.router.navigate(["paypal"]);
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
    let name: string = this.editForm.get("name")?.value ? this.editForm.get("name")?.value :  this.userData.name;
    let surname: string = this.editForm.get("surname")?.value ? this.editForm.get("surname")?.value :  this.userData.surname;
    let city: string = this.editForm.get("city")?.value ? this.editForm.get("city")?.value :  this.userData.city;
    let phone: string = this.editForm.get("phone")?.value ? this.editForm.get("phone")?.value :  this.userData.phone;

    let c: ChangeProfileRequest = {name: name, surname: surname, email: this.username, picture: "", city: city, phone: phone, carType: "",
      petsAllowed: false, babyAllowed: true, note:"", tokens: 0, blocked: false, password:""};
    this.userDataService.saveUserChanges(c);

  }

  changePasswordIfEntered() {
    if (this.modalForm.get("editPassword")!.value != "") {
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
    this.userDataService.getUserData(this.username).subscribe((response) => {
      this.userData = response;
      this.editForm.get("name")?.setValue(this.userData.name);
      this.editForm.get("surname")?.setValue(this.userData.surname);
      this.editForm.get("email")?.setValue(this.userData.email);
      this.editForm.get("password")?.setValue("lozinka");
      this.editForm.get("city")?.setValue(this.userData.city);
      this.editForm.get("phone")?.setValue(this.userData.phone);
      document.getElementById("tokensLbl")!.innerHTML = this.userData.tokens.toString() + " ";

      console.log(response)
      var retrieveResonse = response;
      var base64Data = retrieveResonse.picture;
      this.retrievedImage = 'data:image/jpeg;base64,' + base64Data;

    });


  }

}


