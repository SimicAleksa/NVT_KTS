import { Component, OnInit } from '@angular/core';
import { APIRequestMaker } from '../../../../utils/api-request-maker';


@Component({
  selector: 'app-user-drives-history-page',
  templateUrl: './user-drives-history-page.component.html',
  styleUrls: ['./user-drives-history-page.component.css']
})
export class UserDrivesHistoryPageComponent implements OnInit {
 public ridesHistory: Array<any>;

  constructor(private reqMaker: APIRequestMaker) {
    this.ridesHistory = [];
  }

  ngOnInit(): void {
    this.getRideHistory();
  }

  getRideHistory(): void { 
    this.reqMaker.creteUserRidesHistoryRequest().subscribe(this.getObservable());
  }

  getObservable() {
    return {
      next: (retData: any) => {
        this.ridesHistory = retData.body;
      },
      error: (err: any) => {
        this.ridesHistory = [];
      },
      complete: () => {
      }
    }
  }
}

