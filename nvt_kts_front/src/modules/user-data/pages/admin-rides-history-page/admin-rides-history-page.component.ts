import { Component, OnInit } from '@angular/core';
import { APIRequestMaker } from 'src/utils/api-request-maker';

@Component({
  selector: 'app-admin-rides-history-page',
  templateUrl: './admin-rides-history-page.component.html',
  styleUrls: ['./admin-rides-history-page.component.css']
})
export class AdminRidesHistoryPageComponent implements OnInit {
  public ridesHistory: Array<any>;

  public showTable: boolean;

  public usrEmail: string;

  constructor(private reqMaker: APIRequestMaker) {
    this.ridesHistory = [];
    this.showTable = false;
    this.usrEmail = "";
  }

  ngOnInit(): void {
  }

  onSubmitEmailBtnClick(): void {
    this.getRideHistory();  
  }

  getRideHistory(): void { 
    this.reqMaker.creteAdminRidesHistoryRequest(this.usrEmail).subscribe(this.getObservable());
  }

  getObservable() {
    return {
      next: (retData: any) => {
        this.showTable = true;
        this.ridesHistory = retData.body;
        this.convertJsonStrToObj();
        this.scrollToHistoryTable();
      },
      error: (err: any) => {
        this.ridesHistory = [];
      },
      complete: () => {
      }
    }
  }

  convertJsonStrToObj() {
    if (this.ridesHistory !== undefined)
      this.ridesHistory.forEach(elem => {
        elem.ride.route.routeJSON = JSON.parse(elem.ride.route.routeJSON);
      });
  }

  scrollToHistoryTable(): void {
    this.doDaScroll('#history-table');
  }

  scrollToDetailsPanel(): void {
    this.doDaScroll('#details-panel');
  }

  doDaScroll(elemId: string) {
    let element = document.querySelector(elemId);
    let elementPosition = element!.getBoundingClientRect();
    if (elementPosition.height === 0) {
      setTimeout(() => {
        element = document.querySelector(elemId);
        elementPosition = element!.getBoundingClientRect();
        if (elementPosition !== undefined)
          window.scrollTo({ 
            top: elementPosition.top,
            behavior: 'smooth'
          });
          
      }, 300);
    }

    if (elementPosition !== undefined)
      window.scrollTo({ 
        top: elementPosition.top,
        behavior: 'smooth'
      });
  }

}
