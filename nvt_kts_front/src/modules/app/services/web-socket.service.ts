/*import { Injectable } from '@angular/core';

const SockJs: any = require('sockjs-client');
const Stomp: any = require('stompjs');


@Injectable
({
  providedIn: 'root'
})
export class WebSocketService {
  public connect() {
    //const tok = localStorage.getItem('userToken');
    let stompClient;
    // ovdje je bilo neki IF za tokene koji sam obrisala
    const socket = new SockJs(`http://localhost:8000/sockett`);
    stompClient = Stomp.over(socket);
  
    return stompClient;
  }
}*/