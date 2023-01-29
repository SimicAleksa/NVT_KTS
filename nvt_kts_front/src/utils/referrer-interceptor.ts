import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class ReferrerInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const item = localStorage.getItem("token");
    if (item) {
      request = request.clone({
        setHeaders: {
          'Referrer-Policy': 'no-referrer-when-downgrade',
          "Authorization":"Bearer " + item
        }
      });
      return next.handle(request);
    } else {
      return next.handle(request);
    }
  }
}
