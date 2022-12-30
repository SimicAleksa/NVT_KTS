import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CLIENT_LOGIN_URL, CLIENT_REGISTRATION_URL } from 'src/config/client-urls';
import { MenuService } from '../../service/menu-service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  public role: string;

  constructor(private menuService: MenuService, private router: Router) {
    this.role = '';
    this.updateRole();
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
    this.updateRole();
    this.router.navigate([CLIENT_LOGIN_URL]);
  }

  onLoginBtnClick(): void {
    this.router.navigate([CLIENT_LOGIN_URL]);
  }
  
  onRegisterBtnClick(): void {
    this.router.navigate([CLIENT_REGISTRATION_URL]);
  }
}
