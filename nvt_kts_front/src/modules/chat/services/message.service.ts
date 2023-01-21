import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from 'src/modules/app/model/message';
import { MessagePerson } from 'src/modules/app/model/messagePerson';
import { ReportParam } from 'src/modules/app/model/reportParams';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  
  
  private headers = new HttpHeaders({ "Content-Type": "application/json"});


  private messagesUrl: string;
  private userDTOChatUrl: string;
  private userMessageMapUrl: string; 

  constructor(private http: HttpClient) {
    this.messagesUrl = 'api/messages/getUserMessages';
    this.userDTOChatUrl = 'api/messages/getUsersDTOsForChat/';
    this.userMessageMapUrl = 'api/messages/getUserMessagesMap/';
   }

   sendMessage() {
    let m: Message = {sender: "sender", receiver: "receiver", text: "sadrzaj poruke"};
    //return this.http.post<string>("api/messages/addMessage", m);
    return this.http.post("api/messages/addMessage", m, {
      headers: this.headers,
      responseType: "json",      
    }).subscribe(() => {
    });
  }

  gadjajMladjino(): void {
    alert("gadjala sam Mladjin servis");
    this.http.get('api/notifyWaiter', {
        headers: this.headers,
        responseType: 'json',
      })
      .subscribe(() => {
        //this.toastr.success('Notify successfull!');
        alert("Alert iz message servisa u subscribe");
      });
  }

   getUsersMessages(): Observable<Message[]>{
    return this.http.get<Message[]>(this.messagesUrl, {
      headers: this.headers,
      responseType: "json",
    });
  }

  // ova funkcija na osnovu mejla dobavlja ime, prezime, mejl i sliku osobe
  getUsersToShowInMessages(email: string): Observable<MessagePerson[]> {
    return this.http.get<MessagePerson[]>(this.userDTOChatUrl + email, {
      headers: this.headers,
      responseType: "json",
    });
  }

  getUserMessageMap(email: string): Observable<Map<string, Message[]>> {
    return this.http.get<Map<string, Message[]>>(this.userMessageMapUrl + email, {
      headers: this.headers,
      responseType: "json",
    });
  }
}
