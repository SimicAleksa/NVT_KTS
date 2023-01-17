import { Component, OnInit } from '@angular/core';
import { AnonymousSubject } from 'rxjs/internal/Subject';
import { Message } from 'src/modules/app/model/message';
import { MessagePerson } from 'src/modules/app/model/messagePerson';
import { MessageService } from '../../services/message.service';

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
  public myName: string;


  constructor(
    private messageService: MessageService, 
  ) { 
    this.userEmail = "zima@gmail.com";
    this.usersFriend = "pera@gmail.com";
    
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


  ngOnInit(): void {
    this.messageService.getUserMessageMap(this.userEmail).subscribe((response) => {
      this.messagesMap = new Map(Object.entries(response));
      this.currentMessages = this.messagesMap.get(this.usersFriend);
      this.messageService.getUsersToShowInMessages(this.userEmail).subscribe((response) => {
        this.messagePersons = response;
        this.friendName = this.findName(this.usersFriend);
        this.myName = this.findName(this.userEmail);
        this.addLastMessages();

      })
    });
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
    let l:HTMLCollectionOf<Element> = document.getElementsByClassName("allWhite"); 
    for(var i = 0; i<l.length; i++)
    {
      //l.item(i)?.getElementsByClassName.setProperty('background-color', 'white');
      (l.item(i) as HTMLElement).style.setProperty('background-color', 'white');
    }
    document.getElementById(email)?.style.setProperty('background-color', 'rgb(246, 241, 241)');
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

