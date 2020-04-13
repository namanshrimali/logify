import { Component, OnInit, ViewChild } from '@angular/core';
import { ManagerService } from '../service/manager.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
export interface UserData {
  name: string,
  userName: string,
  role: string
}

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.sass']
})
export class EmployeeListComponent implements OnInit {
  displayedColumns: string[] = ["username", 'name', 'role'];
  dataSource: MatTableDataSource<UserData>;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  constructor(private managerService: ManagerService) {
    this.dataSource = new MatTableDataSource();
    this.managerService.getAllEmployees().subscribe(res=> {
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
}
