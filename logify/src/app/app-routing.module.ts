import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogsComponent } from './manager/logs/logs.component';
import { EmployeeListComponent } from './manager/employee-list/employee-list.component';
import { LoginComponent } from './user-info/login/login.component';
import { ManagerComponent } from './manager/manager/manager.component';
import { RegisterComponent } from './user-info/register/register.component';
import { UserComponent } from './user-info/user/user.component';
import { EmployeeDashboardComponent } from './employee/employee-dashboard/employee-dashboard.component';
import { AuthGuard } from './auth.guard';


const routes: Routes = [
{
  path: 'user',
  component: UserComponent,
  children: [
    {
      path: '',
      redirectTo: 'login',
      pathMatch: 'full'
    },
    {
      path: 'login',
      component: LoginComponent
    },
    {
      path: 'register',
      component: RegisterComponent
    },
  ]
},
{
  path: 'manager',
  component: ManagerComponent,
  canActivate: [AuthGuard],
  children: [
    {
      path: '',
      redirectTo: 'logs',
      pathMatch: 'full'
    },
    {
      path: 'logs',
      component: LogsComponent
    },

    {
      path: 'my-employees',
      component: EmployeeListComponent
    }
  ]
},
{
  path: 'employee',
  component: EmployeeDashboardComponent,
  canActivate: [AuthGuard]
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
