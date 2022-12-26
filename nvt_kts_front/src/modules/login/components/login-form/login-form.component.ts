import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  @Output() errSignal = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  emitError() {
    this.errSignal.emit('Some error ');
  }

}
