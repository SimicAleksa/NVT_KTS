import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest } from '@angular/common/http';
import { API_LOGIN_URL, API_SEND_PASS_RESET_EMAIL } from "../config/api-urls";


@Injectable({ providedIn: 'root' })
export class APIRequestMaker {
  constructor(private httpClient: HttpClient) { }

  createLoginRequest(data: any) {
    return this.getRequest('POST', API_LOGIN_URL, data);
  }

  createForgottenPasswordRequest(emailArg: string) {
    return this.getRequest('GET', API_SEND_PASS_RESET_EMAIL + emailArg);
  }

  getRequest(requestType: string, api_url: string, data: any = null) {
    return this.httpClient.request(new HttpRequest(requestType, api_url, data));
  }
}
