import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/modules/app/model/user';
import { UserDataService } from '../../services/user-data.service';

@Component({
  selector: 'app-block-user',
  templateUrl: './block-user.component.html',
  styleUrls: ['./block-user.component.css']
})
export class BlockUserComponent implements OnInit {
  users: User[] = new Array<User>;
  modalForm: FormGroup;
  displayStyle = "none";
  currentUser : string;

  constructor(
    private userDataService : UserDataService, 
    private fb: FormBuilder
  ) { 
    this.createModalForm();
  }

  createModalForm() {
    this.modalForm = this.fb.group({
      note: ["", [Validators.required]],
    },);
  }


  openPopup() {
    this.displayStyle = "block";
  }
  closePopup() {
    this.displayStyle = "none";
  }

  onModalSubmit()
  {
    let newNote: string = this.modalForm.get("note")?.value;
    this.modalForm.get("note")?.setValue("");
    this.displayStyle = "none";
    this.setUserNote(newNote);
    this.userDataService.addNote(newNote, this.currentUser)    
  }

  setUserNote(newNote: string) {
    // treba pronaci trenutnog usera i dodati mu note
    for(let user of this.users)
    {
      if (user.email==this.currentUser)
      {
        let currNote: string = user.note ? user.note + ", " : "";
        user.note = currNote + " " + newNote;
        return;
      }
    }
  }


  ngOnInit(): void {
    this.userDataService.getAllUserData().subscribe((response) => {
      this.users = response;      
    });
  }

  addNote(email: string)
  {
    this.displayStyle = "block";
    this.currentUser = email.split('|')[0];
  }

  block(email: string)
  {
    this.currentUser = email.split('|')[0];
    this.userDataService.blockUser(this.currentUser, true);
    this.setUserBlock(true);
  }

  setUserBlock(blocked: boolean) {
    // treba pronaci trenutnog usera i blokirati ga
    for(let user of this.users)
    {
      if (user.email==this.currentUser)
      {
        user.blocked = blocked;
        return;
      }
    }
  }

  unblock(email: string)
  {
    this.currentUser = email.split('|')[0];
    this.userDataService.blockUser(this.currentUser, false);
    this.setUserBlock(false);
  }

}
