import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search-routes-page',
  templateUrl: './search-routes-page.component.html',
  styleUrls: ['./search-routes-page.component.css']
})
export class SearchRoutesPageComponent implements OnInit {

  public selectedLocation:any;

  constructor() { }

  ngOnInit(): void {
  }

  recieveSentSelectedLocation($event: any){
    this.selectedLocation = $event;
  }

}
