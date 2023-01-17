import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  editForm: FormGroup;
  modalForm: FormGroup;


  constructor(private fb: FormBuilder) {
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

  phoneChanged()
  {
    this.editForm.controls['phone'].enable();
  }

  onSubmit()
  {
    console.log("Poslala sam");
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
    this.editForm.get("name")?.setValue("Ime");
    this.editForm.get("surname")?.setValue("Prezime");
    this.editForm.get("email")?.setValue("email");
    this.editForm.get("password")?.setValue("lozinka");
    this.editForm.get("city")?.setValue("grad");
    this.editForm.get("phone")?.setValue("telefon");
    
  }

}


