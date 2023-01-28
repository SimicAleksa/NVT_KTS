import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserDataService } from '../../services/user-data.service';
import { User } from 'src/modules/app/model/user';
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


  constructor(
    private fb: FormBuilder,
    private userDataService: UserDataService,
    private router: Router,
    ) {
    this.username = "registrovani1@gmail.com";
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
    let picture: number[] = this.findPictureBytes();
    /*var reader = new FileReader();
    var fileByteArray = [];
    reader.readAsArrayBuffer(myFile);
    reader.onloadend = function (evt) {
        if (evt.target?.readyState == FileReader.DONE) {
          var arrayBuffer = evt.target.result,
              array = new Uint8Array(arrayBuffer);
          for (var i = 0; i < array.length; i++) {
              fileByteArray.push(array[i]);
            }
        }
    }*/

  }

  findPictureBytes(): number[] {
    
    let inp : HTMLInputElement = document.querySelector("#files")!;
    let value: string = inp.value;
    let root: string[] = value.split("\\");
    let size: number = root.length;
    let full: string = "C:\\Users\\Nevena\\Desktop\\" + root.at(size - 1);
    alert("Putanja bi trebalo da je " + full);
   
    

  }

  


  onModalSubmit()
  {
    alert("zatvoreno")
    this.displayStyle = "none";
    //zatvorila sam modal
    // treba jos tu vrijednost da upisem u input polje za sifru
    alert(this.modalForm.get("editPassword")?.value + " je ova ")
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
      
    });
    
    
  }

}


