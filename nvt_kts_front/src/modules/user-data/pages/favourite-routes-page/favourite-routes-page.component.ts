import { Component, OnInit } from '@angular/core';
import { APIRequestMaker } from 'src/utils/api-request-maker';

@Component({
  selector: 'app-favourite-routes-page',
  templateUrl: './favourite-routes-page.component.html',
  styleUrls: ['./favourite-routes-page.component.css']
})
export class FavouriteRoutesPageComponent implements OnInit {
  public routes: Array<any>;

  constructor(private reqMaker: APIRequestMaker) { 
    this.routes = new Array;
    this.fetchFavouriteRoutes();
  }

  ngOnInit(): void {
  }

  fetchFavouriteRoutes(): void {
    this.reqMaker.createGetAllFavouriteRoutesRequest().subscribe(this.getObservable());
  }

  getObservable() {
    return {
      next: (retData: any) => {
        this.routes = retData.body;
        this.convertJsonStrToObj();
      },
      error: (err: any) => {
        this.routes = [];
      },
      complete: () => {
      }
    }
  }

  convertJsonStrToObj() {
    if (this.routes !== undefined)
      this.routes.forEach(elem => {
        elem.routeJSON = JSON.parse(elem.routeJSON);
      });
  }

}
