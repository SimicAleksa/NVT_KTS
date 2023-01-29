import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(public router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles: string = route.data["expectedRoles"];
    const token = localStorage.getItem("token");
    const tokenRole = localStorage.getItem("role");
    const roles: string[] =expectedRoles.split("|")
    if (!token) {
      this.router.navigate(["login"]);
      return false;
    }

    if(!roles.includes(tokenRole!)){
      this.router.navigate(["login"]);
      return false;
    }
    
    return true;
  }
}
