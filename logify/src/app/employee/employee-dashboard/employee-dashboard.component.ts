import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { UserLogData } from 'src/app/manager/logs/logs.component';
import { EmployeeService } from '../service/employee.service';

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
/** Constants used to fill up our data base. */

@Component({
  selector: 'app-employee-dashboard',
  templateUrl: './employee-dashboard.component.html',
  styleUrls: ['./employee-dashboard.component.sass'],
})
export class EmployeeDashboardComponent implements OnInit {
  isLoading = true;
  hasDayStarted = false
  hasDayEnded = false;
  elementData:UserLogData[] = [];
  displayedColumns: string[] = ['date', 'startTime', 'endTime', 'total'];
  dataSource: MatTableDataSource<UserLogData> = new MatTableDataSource();;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private employeeService: EmployeeService) {
    this.employeeService.getUserLogHistory(true).subscribe(res=> {
      if(res[0] != null) {
        if (res[0].endTime ==null) {
          this.hasDayStarted = true;
        } else {
          this.hasDayEnded = true;
        }
      }
     this.isLoading= false;
   }); 
    this.employeeService.getUserLogHistory(false).subscribe(res=> {
       if(res.length != 0) {
         this.dataSource = new MatTableDataSource(res);
         
       }
    });
  }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  startLogging() {
    this.employeeService.startLogging().subscribe(res=> {
      this.elementData.unshift(res);
      this.dataSource = new MatTableDataSource(this.elementData);
      this.hasDayStarted = true;
    })
  }
  endLogging() {
    this.employeeService.endLogging().subscribe(res=> {
      this.elementData = this.elementData.filter(data=> data.startTime !== res.startTime);
      this.elementData.unshift(res);
      this.dataSource = new MatTableDataSource(this.elementData);
      this.hasDayEnded = true
    })
  }
}

