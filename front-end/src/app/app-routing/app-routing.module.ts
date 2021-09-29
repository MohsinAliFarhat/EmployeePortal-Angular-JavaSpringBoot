import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AddEmployeeComponent } from '../add-employee/add-employee.component';
import { NavBarComponent } from '../nav-bar/nav-bar.component';
import { EmployeeListComponent } from '../employee-list/employee-list.component';
import { EditEmployeeComponent } from '../edit-employee/edit-employee.component';
const routes: Routes = [
  { path: '', redirectTo: "add-employee", pathMatch: "full" },
  { path: 'employee-list', component: EmployeeListComponent },
  { path: 'add-employee', component: AddEmployeeComponent },
  { path: 'employees/:id', component: EditEmployeeComponent },
  {    path: '**', component: NavBarComponent  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }