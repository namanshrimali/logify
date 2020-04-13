import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserLogData } from 'src/app/manager/logs/logs.component';
import { AuthService } from 'src/app/service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  baseUrl: string = "http://localhost:8080/api/v1/employee"
  constructor(private http: HttpClient, private authService: AuthService) { }

  endLogging(): Observable <UserLogData>{
    let username = this.authService.parseJwt(localStorage.getItem('logify_user')).sub
    return this.http.post<UserLogData>(`${this.baseUrl}/${username}/checkout`, null);
  }
  getUserLogHistory(lastActiveSession: boolean):Observable <UserLogData[]> {
    let username = this.authService.parseJwt(localStorage.getItem('logify_user')).sub
    if(lastActiveSession) {
      return this.http.get<UserLogData[]>(`${this.baseUrl}/${username}/history?lastActiveSession=${lastActiveSession}`);
    }
    return this.http.get<UserLogData[]>(`${this.baseUrl}/${username}/history`);
  }
  startLogging(): Observable <UserLogData>{
    let username = this.authService.parseJwt(localStorage.getItem('logify_user')).sub
    return this.http.post<UserLogData>(`${this.baseUrl}/${username}/checkin`, null);
  }
}
