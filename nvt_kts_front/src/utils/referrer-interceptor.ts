import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class ReferrerInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    request = request.clone({
      setHeaders: {
        'Referrer-Policy': 'no-referrer-when-downgrade',
        "Authorization": `Bearer ${localStorage.getItem("token") || 'invalid'}`
      }
    });
    return next.handle(request);
  }
}
