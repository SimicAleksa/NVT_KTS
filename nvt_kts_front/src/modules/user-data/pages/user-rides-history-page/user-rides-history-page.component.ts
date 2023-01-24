import { Component, OnInit } from '@angular/core';
import { APIRequestMaker } from '../../../../utils/api-request-maker';


@Component({
  selector: 'app-user-rides-history-page',
  templateUrl: './user-rides-history-page.component.html',
  styleUrls: ['./user-rides-history-page.component.css']
})
export class UserRidesHistoryPageComponent implements OnInit {
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

  scrollToDetailsPanel(): void {
    let element = document.querySelector('#details-panel');
    let elementPosition = element!.getBoundingClientRect();
    if (elementPosition.height === 0) {
      setTimeout(() => {
        element = document.querySelector('#details-panel');
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

