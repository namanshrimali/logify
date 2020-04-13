import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UserLogData } from '../logs/logs.component';
import { UserData } from '../employee-list/employee-list.component';

@Injectable({
  providedIn: 'root'
})
export class ManagerService {
  baseUrl="http://localhost:8080/api/v1/manager"
  constructor(private http: HttpClient) { }
  getEmployeeLog(date: string) :Observable<UserLogData[]>{
    return this.http.get<UserLogData[]>(`${this.baseUrl}/employeeLog?date=${date}`);

  }
  getAllEmployees() :Observable<UserData[]>{
    return this.http.get<UserData[]>(`${this.baseUrl}/allUsers`);
  }
}
