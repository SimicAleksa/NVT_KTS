import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from 'src/modules/app/model/message';
import { MessagePerson } from 'src/modules/app/model/messagePerson';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  
  private headers = new HttpHeaders({ "Content-Type": "application/json"});


  private messagesUrl: string;
  private userDTOChatUrl: string;
  private userMessageMapUrl: string; 

  constructor(private http: HttpClient) {
    this.messagesUrl = 'http://localhost:8000/getUserMessages';
    this.userDTOChatUrl = 'http://localhost:8000/getUsersDTOsForChat/';
    this.userMessageMapUrl = 'http://localhost:8000/getUserMessagesMap/';
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
