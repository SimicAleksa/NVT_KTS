import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-car',
  templateUrl: './add-car.component.html',
  styleUrls: ['./add-car.component.css']
})
export class AddCarComponent implements OnInit {
  carTypes: string[];
  selectedType: string = "";

  constructor() {
    this.carTypes = ["SUV", "Hatchback", "Coupe", "Minivan", "Sedan", "Van", "Limousine"];
   }

  ngOnInit(): void {
  }

 
}
