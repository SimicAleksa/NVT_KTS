import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-simple-routes-search-page',
  templateUrl: './simple-routes-search-page.component.html',
  styleUrls: ['./simple-routes-search-page.component.css']
})
export class SimpleRoutesSearchPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  ngAfterViewInit() {
    var elements:any = document.getElementsByClassName("leaflet-routing-add-waypoint");
    for(var el of elements)
      el.style.display="none";
  }

}
