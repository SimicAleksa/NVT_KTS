import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CLIENT_ADMIN_RIDES_HISTORY_URL, CLIENT_DRIVER_RIDES_HISTORY_URL, CLIENT_LOGIN_URL, CLIENT_REGISTRATION_URL, CLIENT_REG_USR_RIDES_HISTORY_URL, CLIENT_USER_FAV_ROUTES } from 'src/config/client-urls';
import { API_ALL_ACTIVE_VEHICLES_URL, API_ROUTE_SEARCH, API_SIMPLE_ROUTE_SEARCH } from 'src/config/map-urls';
import { MenuService } from '../../service/menu-service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  public role: string;
  public currentActiveBtn: string;

  constructor(private menuService: MenuService, private router: Router) {
    this.role = '';
    this.updateRole();
    
    if (this.role === "USER")
      this.currentActiveBtn = "HOME";
    else
      this.currentActiveBtn = "LOGIN";
  }

  ngOnInit(): void {
    this.menuService.menuRole.subscribe(() => {
      this.updateRole();
    });
  }

  updateRole(): void {
    this.role = localStorage.getItem('role') || 'UNREGISTERED';
  }

  onLogoutBtnClick(): void {
    localStorage.removeItem('role');
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    this.updateRole();

    this.currentActiveBtn = 'LOGIN';
    this.router.navigate([CLIENT_LOGIN_URL]);
  }

  onLoginBtnClick(): void {
    this.currentActiveBtn = 'LOGIN';
    this.router.navigate([CLIENT_LOGIN_URL]);
  }
  
  onRegisterBtnClick(): void {
    this.currentActiveBtn = 'REGISTER';
    this.router.navigate([CLIENT_REGISTRATION_URL]);
  }

  onAllActiveVehiclesBtnClick(): void {
    this.currentActiveBtn = 'ALL ACTIVE VEHICLES';
    this.router.navigate([API_ALL_ACTIVE_VEHICLES_URL]);
  }
  onSimpleRouteSearchBtnClick(): void {
    this.currentActiveBtn = 'SIMPLE ROUTE SEARCH';
    this.router.navigate([API_SIMPLE_ROUTE_SEARCH]);
  }

  onRouteSearchBtnClick(): void {
    this.currentActiveBtn = 'ROUTE SEARCH';
    this.router.navigate([API_ROUTE_SEARCH]);
  }

  onUserRidesHistoryBtnClick(): void { 
    this.currentActiveBtn = 'HISTORY';
    this.router.navigate([CLIENT_REG_USR_RIDES_HISTORY_URL]);
  }
  
  onDriverRidesHistoryBtnClick(): void { 
    this.currentActiveBtn = 'HISTORY';
    this.router.navigate([CLIENT_DRIVER_RIDES_HISTORY_URL]);
  }

  onAdminRidesHistoryBtnClick(): void { 
    this.currentActiveBtn = 'HISTORY';
    this.router.navigate([CLIENT_ADMIN_RIDES_HISTORY_URL]);
  }

  onFavouriteRoutesBtnClick(): void { 
    this.currentActiveBtn = 'FAVOURITE_ROUTES';
    this.router.navigate([CLIENT_USER_FAV_ROUTES]);
  }

}
