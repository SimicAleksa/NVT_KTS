import { Component, OnInit } from '@angular/core';
import { ProbaService } from '../../services/proba.service';


@Component({
  selector: 'app-proba-page',
  templateUrl: './proba-page.component.html',
  styleUrls: ['./proba-page.component.css']
})
export class ProbaPageComponent implements OnInit {

  
  private username: string = "djura@gmail.com";
  

  constructor(private probaService: ProbaService){
    
  }


  ngOnInit(): void {
    this.probaService.getMinutes(this.username).subscribe((response) => {
      alert(response);
    });
  }


  


}