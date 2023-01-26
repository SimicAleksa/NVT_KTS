import { Component, OnInit } from '@angular/core';
import { APIRequestMaker } from 'src/utils/api-request-maker';

@Component({
  selector: 'app-driver-rides-history-page',
  templateUrl: './driver-rides-history-page.component.html',
  styleUrls: ['./driver-rides-history-page.component.css']
})
export class DriverRidesHistoryPageComponent implements OnInit {
  public ridesHistory: Array<any>;

  constructor(private reqMaker: APIRequestMaker) {
    this.ridesHistory = [];
   }

   ngOnInit(): void {
    this.getRideHistory();
  }

  getRideHistory(): void { 
    this.reqMaker.creteDriverRidesHistoryRequest().subscribe(this.getObservable());
  }

  getObservable() {
    return {
      next: (retData: any) => {
        this.ridesHistory = retData.body;
        this.convertJsonStrToObj();
        console.log(this.ridesHistory);
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
