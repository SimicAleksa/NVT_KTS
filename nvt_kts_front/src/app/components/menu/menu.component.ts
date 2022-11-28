import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  animations: [  
    trigger('cogwheelRotate', [
      state('true', style({transform: 'rotate(0deg)'})),
      state('false', style({transform: 'rotate(359deg)'})),
      transition('* <=> *', [animate(800)])
    ])
  ]
})
export class MenuComponent implements OnInit {
  isCogwheelAnimReverse: Boolean;
  public state:string = 'endRound';
  constructor() {
    this.isCogwheelAnimReverse= false;
  }

  ngOnInit(): void {
  }

  reverseAnimationState(): void {
    this.isCogwheelAnimReverse = !this.isCogwheelAnimReverse;
  }

}
