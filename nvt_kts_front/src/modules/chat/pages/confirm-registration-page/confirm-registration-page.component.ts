import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserDataService } from 'src/modules/user-data/services/user-data.service';

@Component({
  selector: 'app-confirm-registration-page',
  templateUrl: './confirm-registration-page.component.html',
  styleUrls: ['./confirm-registration-page.component.css']
})
export class ConfirmRegistrationPageComponent implements OnInit {

  private email: string;

  constructor(private userDataService: UserDataService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    // ovdje odmah treba da odobrim profil
    this.email = this.getEmailFromUrl();
    alert("Trenutni mejl je " + this.email);
    this.userDataService.activateProfile(this.email);
  }



  getEmailFromUrl(): string {
    let email: string = '';
    this.activatedRoute.paramMap.subscribe(paramMap => {
      email = paramMap.get('email') || '';
    });
    return email;
  }

}
