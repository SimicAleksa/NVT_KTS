import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { API_LOGIN_URL, API_SEND_PASS_RESET_EMAIL_URL, API_PASS_RESET_URL, API_FB_LOGIN_URL, API_DRIVER_REVIEWS_URL, API_NEW_REVIEWS_URL, API_USER_RIDE_HISTORY_URL, API_DRIVER_RIDE_HISTORY_URL, API_ADMIN_RIDE_HISTORY_URL, API_ADD_ROUTE_TO_FAV_URL, API_GET_ALL_FAV_ROUTES, API_REMOVE_ROUTE_FROM_FAV, API_GOOGLE_LOGIN_URL } from "../config/api-urls";


@Injectable({ providedIn: 'root' })
export class APIRequestMaker {
  constructor(private httpClient: HttpClient) { }

  createLoginRequest(data: any) {
    return this.getRequest('POST', API_LOGIN_URL, data);
  }

  createFacebookLoginRequest(data: any) {
    return this.getRequest('POST', API_FB_LOGIN_URL, data);
  }

  createGoogleLoginRequest(data: any) {
    return this.getRequest('POST', API_GOOGLE_LOGIN_URL, data);
  }

  createForgottenPasswordRequest(emailArg: string) {
    return this.getRequest('GET', API_SEND_PASS_RESET_EMAIL_URL + emailArg);
  }

  createPasswordResetRequest(data: any) {
    return this.getRequest('PUT', API_PASS_RESET_URL, data);
  }

  creteUserRidesHistoryRequest() {
    return this.getRequest('GET', API_USER_RIDE_HISTORY_URL);
  }
  
  createDriverReviewsRequest(driverId: number) {
    return this.getRequest('GET', API_DRIVER_REVIEWS_URL+"?driverId="+driverId);
  }

  createNewReviewRequest(data: any) {
    return this.getRequest('POST', API_NEW_REVIEWS_URL, data);
  }
  
  creteDriverRidesHistoryRequest() {
    return this.getRequest('GET', API_DRIVER_RIDE_HISTORY_URL);
  }
  
  creteAdminRidesHistoryRequest(usrEmail: string) {
    return this.getRequest('GET', API_ADMIN_RIDE_HISTORY_URL+"?email="+usrEmail);
  }

  createNewFavoriteRouteRequest(routeId: number) {
    return this.getRequest('PUT', API_ADD_ROUTE_TO_FAV_URL+"?routeId="+routeId);
  }

  createGetAllFavouriteRoutesRequest() {
    return this.getRequest('GET', API_GET_ALL_FAV_ROUTES);
  }

  createRemoveRouteFromFavouritesRequest(routeId: number) {
    return this.getRequest('PUT', API_REMOVE_ROUTE_FROM_FAV+"?routeId="+routeId);
  }

  getRequest(requestType: string, api_url: string,data: any = null) {
    // let header = {
    //   headers: new HttpHeaders().set('Authorization',  `Bearer ${localStorage.getItem("token") || 'invalid'}`)
    // }
    return this.httpClient.request(new HttpRequest(requestType, api_url, data));
  }
}
