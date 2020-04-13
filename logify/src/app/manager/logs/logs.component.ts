import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

import {FormControl} from '@angular/forms';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import * as _moment from 'moment';
import {MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';
import {default as _rollupMoment} from 'moment';
import { ManagerService } from '../service/manager.service';


const moment = _rollupMoment || _moment;


export interface UserLogData {
  id: string;
  name: string;
  startTime?: string;
  endTime?: string;
  total?: string;
}
export const MY_FORMATS = {
  parse: {
    dateInput: 'LL',
  },
  display: {
    dateInput: 'LL',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};
@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.sass'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },

    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },]
})
export class LogsComponent implements OnInit {
  date = new FormControl(moment());
  displayedColumns: string[] = ["username", 'name', 'startTime', 'endTime', 'total'];
  dataSource: MatTableDataSource<UserLogData> = new MatTableDataSource();
  @Input() max: any;
  today = new Date();
  minDate =  new Date(2020, 0, 1)
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  events: string[] = [];

  constructor(private managerService: ManagerService) {
    let month;
    let date;
    let fullDate;
    if(this.today.getMonth()+1<10) {
      month = "0"+(this.today.getMonth()+1);
    } else {month = ""+this.today.getMonth()+1}
    if(this.today.getDate()<10) {
      date = "0"+this.today.getDate();
    } else {date = "" + this.today.getDate()}
    fullDate = `${this.today.getFullYear()}-${month}-${date}`
    this.managerService.getEmployeeLog(fullDate).subscribe(res=> {
      this.dataSource = new MatTableDataSource(res);
    });
  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  changeDate(type: string, event) {
    this.managerService.getEmployeeLog(this.formatDate(event.value._i)).subscribe(res=> {
      this.dataSource = new MatTableDataSource(res);
    });
  }
  formatDate(dateObject) {
    let month = dateObject.month+1;
    if(month<10) {
      month = "0"+month
    }
    let date =dateObject.date;
    if(date<10) {
      date="0"+date
    }
    return date = `${dateObject.year}-${month}-${date}`
  }
}