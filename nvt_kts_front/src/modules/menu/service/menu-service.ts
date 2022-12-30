import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MenuService {
  private menuSubject = new BehaviorSubject<boolean>(false);
  menuRole = this.menuSubject.asObservable();

  updateMenu() {
    this.menuSubject.next(true);
  }
}