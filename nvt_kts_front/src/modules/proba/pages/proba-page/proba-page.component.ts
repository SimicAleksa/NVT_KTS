import { Component, OnInit } from '@angular/core';
import {render} from "creditcardpayments/creditCardPayments";


@Component({
  selector: 'app-proba-page',
  templateUrl: './proba-page.component.html',
  styleUrls: ['./proba-page.component.css']
})
export class ProbaPageComponent implements OnInit {
  value:string = "20";
  

  constructor(){
    
  }


  ngOnInit(): void {
  }

  setValue()
  {
    
    //this.value = document.querySelector("#moneyValueInput")!.innerHTML;

    const input = document.getElementById('moneyValueInput') as HTMLInputElement | null;
    this.value = input!.value;    
    console.log(this.value + "je unesena vrijednos");
    render({
      id: "#myPaypalButtons",
      currency: "USD",
      value: this.value,
      onApprove: (details) => {
        alert("Transaction successful");
      }
    })
  }

  


}