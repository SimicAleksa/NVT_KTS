import { Component, OnInit } from '@angular/core';
import { ChangeProfileRequest, User } from 'src/modules/app/model/user';
import { UserDataService } from '../../services/user-data.service';

@Component({
  selector: 'app-approve-changes-page',
  templateUrl: './approve-changes-page.component.html',
  styleUrls: ['./approve-changes-page.component.css']
})
export class ApproveChangesPageComponent implements OnInit {
  public requests : ChangeProfileRequest[][];

  constructor(private userService: UserDataService) { }

  ngOnInit(): void {
    this.userService.getChangedProfiles().subscribe((response) => {
      this.requests = response;      
    });
  }

  approve(email?: string)
  {
    email = email? email : "";  // samo da rijesim jer se crvenilo
    document.getElementById(email)?.classList.add("disabled");
    this.userService.saveChanges(email, this.requests);
  }

  decline(email?: string)
  {
    email = email? email : "";  // samo da rijesim jer se crvenilo
    document.getElementById(email)?.classList.add("disabled");
    this.userService.declineChanges(email, this.requests);
  }

}
