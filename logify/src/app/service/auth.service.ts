import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserLoginData } from '../user-info/model/user-login';
import { HttpClient } from '@angular/common/http';
import { JWTFormat, JWTPayload } from '../user-info/model/jwt-data';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { RegisterUser } from '../user-info/model/user-register';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl: string = "http://localhost:8080/api/v1/user"
  public isUserLoggedIn: BehaviorSubject<boolean> = new BehaviorSubject(false);

  constructor(private http: HttpClient, private router: Router) { }
  
  getJwtToken() {
    return localStorage.getItem('logify_user');
  }
  registerUser(userRegistrationInfo: RegisterUser): Observable<RegisterUser>{
    return this.http.post<RegisterUser>(`${this.baseUrl}/register`, userRegistrationInfo);
  }
  isUserNameUnavailable(username : string):Observable<Boolean> {
    return this.http.get<Boolean>(`${this.baseUrl}/check/${username}`);
  }
  
  isTokenExpired(tokenPayload : JWTPayload) {
    return Date.now()> tokenPayload.exp*1000
  }
  
  isTokenValid(): boolean {
    let cookie = this.getJwtToken();
    if (cookie!=null) {
      if(!this.isTokenExpired(this.parseJwt(cookie))) {
        return true;
      } else {
        this.logoutUser();
        return false;
      }
    } else {
      this.logoutUser();
    }
    return false;
  }

  logoutUser() {
    localStorage.removeItem('logify_user');
    this.isUserLoggedIn.next(false);
    this.router.navigate(['user']);
  }
  
  logInUser(userCredentials: UserLoginData) : Observable<JWTFormat>{
    return this.http.post<JWTFormat>(`${this.baseUrl}/authenticate`, userCredentials);
  }

  parseJwt(token): JWTPayload {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
  };
  routeBasedOnRoles() {
    this.isUserLoggedIn.next(true);
    let jwt =  this.getJwtToken();
    let userRole = this.parseJwt(jwt).authority;
      if (userRole === 'ROLE_USER') {
        this.router.navigate(['/employee'])
      } else {
        this.router.navigate(['/manager'])
      }
    
  }
}
