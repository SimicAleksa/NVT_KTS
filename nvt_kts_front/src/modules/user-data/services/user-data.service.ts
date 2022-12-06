import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  

  constructor() { }

  printHelloWorld(): void{
    console.log("Servis je ispisao Hello world!");
  }

  register() {
    console.log("Ova funkcija dodaje na back novog korisnika");
    console.log("Mozda ga ipak ne doda odmah jer mora provjeriti da li vec postoji sa ovim emailom");
  }
}
