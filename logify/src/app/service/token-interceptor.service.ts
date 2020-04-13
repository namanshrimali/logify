import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor() { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = localStorage.getItem('logify_user')
    let tokenizedRequest;
    if (token) {
      tokenizedRequest = req.clone({
        setHeaders: {
          Authorization: "Bearer " + token
        }
      })
      return next.handle(tokenizedRequest);
    } else {
      return next.handle(req);
    }
  }

}
