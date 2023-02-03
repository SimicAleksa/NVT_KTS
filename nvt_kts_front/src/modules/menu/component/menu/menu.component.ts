import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ADMIN_EDIT_PROF_URL, CLIENT_ADD_DRIVER_URL, CLIENT_ADMIN_RIDES_HISTORY_URL, CLIENT_APPROVEC_CHANGES_URL, CLIENT_BLOCK_USER_URL, CLIENT_DRIVER_RIDES_HISTORY_URL, CLIENT_EDIT_PROF_URL, CLIENT_LOGIN_URL, CLIENT_REGISTRATION_URL, CLIENT_REG_USR_RIDES_HISTORY_URL, CLIENT_USER_FAV_ROUTES, DRIVER_EDIT_PROF_URL } from 'src/config/client-urls';
import { API_ALL_ACTIVE_VEHICLES_URL, API_ROUTE_SEARCH, API_SIMPLE_ROUTE_SEARCH } from 'src/config/map-urls';
import { CLIENT_CHAT_URL } from 'src/config/other-urls';
import { CLIENT_REPORTS_URL, DRIVER_REPORTS_URL, ADMIN_REPORTS_URL } from 'src/config/reports-urls';
import { UserDataService } from 'src/modules/user-data/services/user-data.service';
import { MenuService } from '../../service/menu-service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  public role: string;
  public currentActiveBtn: string;

  constructor(private menuService: MenuService, private userDataService: UserDataService ,private router: Router) {
    this.role = '';
    this.updateRole();

    if (this.role === "UNREGISTERED")
      this.currentActiveBtn = "LOGIN";
    else
      this.currentActiveBtn = "ALL ACTIVE VEHICLES";
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
    if (localStorage.getItem('role')=="DRIVER") this.userDataService.changeDriverActiveStatus(localStorage.getItem('email')!, false);
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

  onAddDriverBtnClick(): void {
    this.currentActiveBtn = 'ADD DRIVER';
    this.router.navigate([CLIENT_ADD_DRIVER_URL]);
  }

  onViewUsersBtnClick(): void {
    this.currentActiveBtn = 'VIEW USERS';
    this.router.navigate([CLIENT_BLOCK_USER_URL]);
  }
////////////////////
  onChangedProfilesBtnClick(): void {
    this.currentActiveBtn = 'CHANGED PROFILES';
    this.router.navigate([CLIENT_APPROVEC_CHANGES_URL]);
  }

  onUserEditProfileBtnClick(): void {
    this.currentActiveBtn = 'EDIT PROFILE';
    this.router.navigate([CLIENT_EDIT_PROF_URL]);
  }
  onDriverEditProfileBtnClick(): void {
    this.currentActiveBtn = 'EDIT PROFILE';
    this.router.navigate([DRIVER_EDIT_PROF_URL]);
  }
  onAdminEditProfileBtnClick(): void {
    this.currentActiveBtn = 'EDIT PROFILE';
    this.router.navigate([ADMIN_EDIT_PROF_URL]);
  }
  onUserReportBtnClick(): void {
    this.currentActiveBtn = 'REPORTS';
    this.router.navigate([CLIENT_REPORTS_URL]);
  }
  onDriverReportBtnClick(): void {
    this.currentActiveBtn = 'REPORTS';
    this.router.navigate([DRIVER_REPORTS_URL]);
  }
  onAdminReportBtnClick(): void {
    this.currentActiveBtn = 'REPORTS';
    this.router.navigate([ADMIN_REPORTS_URL]);
  }

  onChatBtnClick(): void {
    this.currentActiveBtn = 'CHAT';
    this.router.navigate([CLIENT_CHAT_URL]);
  }

}
