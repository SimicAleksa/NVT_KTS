import { Component, OnInit } from '@angular/core';
import { AnonymousSubject } from 'rxjs/internal/Subject';
import { Message } from 'src/modules/app/model/message';
import { MessagePerson } from 'src/modules/app/model/messagePerson';
import { MessageService } from '../../services/message.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ReportParam } from 'src/modules/app/model/reportParams';
//import { WebSocketService } from 'src/modules/app/services/web-socket.service';

@Component({
  selector: 'app-chat-page',
  templateUrl: './chat-page.component.html',
  styleUrls: ['./chat-page.component.css']
})
export class ChatPageComponent implements OnInit {


  public messagesMap: Map<string, Message[]> = new Map<string, Message[]>();

  public messagePersons: MessagePerson[] = [];
  public currentMessages?: Message[] = [];

  public userEmail: string;
  public usersFriend: string;
  public friendName: string;
  private stompClient: any;
  public ws: any;


  constructor(
    private messageService: MessageService, 
    //private webSocketService: WebSocketService,
  ) { 
    this.userEmail = String(localStorage.getItem('email'));
    //TODO pogledaj da li si dobavila
    this.usersFriend = "pera@gmail.com";
  }




  ngOnInit(): void {
    
    this.initializeWebSocketConnection();
    this.messageService.getUserMessageMap(this.userEmail).subscribe((response) => {
      this.messagesMap = new Map(Object.entries(response));
      this.currentMessages = this.messagesMap.get(this.usersFriend);
      this.messageService.getUsersToShowInMessages(this.userEmail).subscribe((response) => {
        this.messagePersons = response;
        this.friendName = this.findName(this.usersFriend);
        //this.myName = this.findName(this.userEmail);
        this.addLastMessages();
        //this.initializeWebSocketConnection();

      })
    });

    this.setEnterToSendMessage();
  }

  setEnterToSendMessage() {  
    let that = this;
    let element : HTMLInputElement = (<HTMLInputElement>document.getElementById('textAreaExample2'));
    element.addEventListener("keydown",function(e){
      if(e.keyCode == 13){
       that.sendMessage();
       element.value = "";
      } 
    });
    element.value = "";
  }
 

  initializeWebSocketConnection() {
    this.ws = new SockJS('http://localhost:8000/socket');
    this.stompClient = Stomp.over(this.ws);
    this.stompClient.debug = null;
    let that = this;
    //alert(that.ws.readyState + " je stanje");
    this.stompClient.connect({}, function () {
      
      that.openGlobalSocket();
      //alert(that.ws.readyState + " je stanje");
    });
  }

  openGlobalSocket()
  {
    this.stompClient.subscribe('/map-updates/add-message', (message: { body: string }) => {
      //this.messageService.saveMessage();
      let m: Message =JSON.parse(message.body);
      console.log(m);

      if (m.sender==this.userEmail || m.receiver == this.usersFriend)
      {
        this.messageService.getUserMessageMap(this.userEmail).subscribe((response) => {
          this.messagesMap = new Map(Object.entries(response));
          this.currentMessages = this.messagesMap.get(this.usersFriend);
          this.messageService.getUsersToShowInMessages(this.userEmail).subscribe((response) => {
            this.messagePersons = response;
            this.addLastMessages();    
          })
        });
      }
      
      // sad treba pogledati cija je poruka i onda vidjeti hocemo li je prikazati
      
    });
  }

  sendMessage()
  {      
      let element : HTMLInputElement = (<HTMLInputElement>document.getElementById('textAreaExample2'));
      this.messageService.sendMessage(element.value, this.userEmail, this.usersFriend);
      element.value = "";
  }

    

  findName(email:string): string {
    // na osnovu mejla dobijem messagePerson, a na osnovu toga ime i prezime
    for (let m of this.messagePersons)
    {
      if (m.email==email)
      {
        return m.name + " " + m.surname;
      }
    }
    return "";
  }



  addLastMessages() {
    for(let p of this.messagePersons)
    {
      p.lastMessage = this.getLastMessage(p.email);
    }
  }

  getLastMessage(email: string): string {
    let l = this.messagesMap.get(email);
    let m:Message = l?.at(l.length - 1)!;
    let s: string = m.text; 
    return s;
  }

  onFriendClick(email: string) {
    this.setAllWhite();
    document.getElementById(email)?.style.setProperty('background-color', 'rgb(246, 241, 241)');
    this.currentMessages = this.messagesMap.get(email);
    this.usersFriend = email;
    this.friendName = this.findName(email);
  }

  setAllWhite() {
    let l:HTMLCollectionOf<Element> = document.getElementsByClassName("allWhite"); 
    for(var i = 0; i<l.length; i++)
    {
      (l.item(i) as HTMLElement).style.setProperty('background-color', 'white');
    }
  }



  /*personAlreadyInMessagePersons(anotherPerson: string) {
    
    for (let x of this.messagePersons)
    {
      alert("Uporedjivala sam stringove: " + x.email + " i " + anotherPerson);
      if (x.email==anotherPerson) 
      {
        return true;
      }
    }
    return false;
  }*/

  /*findAnotherPerson(msg:Message): string {
    if (msg.sender==this.userEmail)
    {return msg.receiver} 
    else 
    return msg.sender;
  }*/

  /*findMessagePersons2() {
    for (let msg of this.messages.reverse())
    {
      // ako nije u listi dodaj ga, a ako jeste, preskoci
      // prvo vidi da li je osoba posiljalac ili primalac
      let anotherPerson: string = this.findAnotherPerson(msg);
      alert("Trenutno another person je " + anotherPerson)
      if (!this.personAlreadyInMessagePersons(anotherPerson))
      {
        alert("Ovu osobu nisam nasla");
        this.messageService.getUserToShowInMessages(anotherPerson).subscribe((response) => {
          alert("Usla sam prije dodavanja");
          this.messagePersons.push({name:response.name, surname:response.surname, picture:response.surname, email:response.email, lastMessage:msg.text});
          alert("Usla sam poslije doodavanja");
        })
      }
    }
  }*/
  

}

