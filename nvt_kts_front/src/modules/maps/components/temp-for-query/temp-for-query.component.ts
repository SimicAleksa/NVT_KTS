import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-temp-for-query',
  templateUrl: './temp-for-query.component.html',
  styleUrls: ['./temp-for-query.component.css']
})
export class TempForQueryComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }
  sendIt(){
    this.router.navigate(
      ['/maps/routeSearch'],
      { queryParams: { order: '3' } }
    );
  }

}
