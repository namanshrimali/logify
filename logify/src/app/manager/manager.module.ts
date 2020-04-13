import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LogsComponent } from './logs/logs.component';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { EmployeeListComponent } from './employee-list/employee-list.component'; 
import {MatToolbarModule} from '@angular/material/toolbar'; 
import {MatTabsModule} from '@angular/material/tabs';
import { ManagerComponent } from './manager/manager.component'; 
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
  declarations: [LogsComponent, EmployeeListComponent, ManagerComponent],
  imports: [
    CommonModule,
    MaterialModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatDatepickerModule,
    ReactiveFormsModule,
    MatTabsModule,
    AppRoutingModule
  ]
})
export class ManagerModule { }
