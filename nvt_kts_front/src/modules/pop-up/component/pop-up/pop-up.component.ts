import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-pop-up',
  templateUrl: './pop-up.component.html',
  styleUrls: ['./pop-up.component.css']
})
export class PopUpComponent implements OnInit {
  @Input() duration: number;
  @Input() isErrorPopUp: boolean;

  public showPopUp: boolean;
  public isFadeClassActive: boolean;
  public message: string;


  constructor() { 
    this.duration = 5000;
    this.isErrorPopUp = true;
    
    this.showPopUp = false;
    this.isFadeClassActive = false;
    this.message = '';
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
