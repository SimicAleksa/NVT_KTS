import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-pop-up',
  templateUrl: './pop-up.component.html',
  styleUrls: ['./pop-up.component.css']
})
export class PopUpComponent implements OnInit {
  @Input() duration: number;
  @Input() message: string;

  showPopUp: boolean;
  isFadeClassActive: boolean;

  
  constructor() { 
    this.duration = 5000;
    this.message = '';
    this.showPopUp = false;
    this.isFadeClassActive = false;
  }

  ngOnInit() {
  }

  closePopUp() {
    this.showPopUp = false;
  }

  startPopUpShowTimeout(message: string) {
    if (this.showPopUp)
      return;

    this.message = message;
    this.showPopUp = true;

    setTimeout(() => {
      this.isFadeClassActive = true;
      setTimeout(() => {
        this.isFadeClassActive = false;
        this.showPopUp = false;
      }, 1000); 
    }, this.duration); 
  }

}
