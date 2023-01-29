import { Component, OnInit } from '@angular/core';
import {render} from "creditcardpayments/creditCardPayments";
import { UserDataService } from 'src/modules/user-data/services/user-data.service';

@Component({
  selector: 'app-paypal',
  templateUrl: './paypal.component.html',
  styleUrls: ['./paypal.component.css']
})

export class PaypalComponent implements OnInit {
  value:string = "2";
  username: string = String(localStorage.getItem('email'));

  constructor(
    private userDataService : UserDataService, 
  ) { 
    
  }

  ngOnInit(): void {
  }

  setValue()
  {
    const input = document.getElementById('moneyValueInput') as HTMLInputElement | null;
    this.value = input!.value;    
    console.log(this.value + "je unesena vrijednos");

    render({
      id: "#myPaypalButtons",
      currency: "USD",
      value: this.value,

      onApprove: (details) => {
        // sad za trenutnog korisnika treba da povecam tokene
        this.userDataService.addTokens(this.value, this.username).subscribe((response) => {
            alert("Transaction successful");
        });
      },

      
    })
    
  }

}
