import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { BackendCommunicationService } from '../services/backend-communication.service';
@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees: any = [];
  length:number=0;
  constructor(private be: BackendCommunicationService, private router:Router) { }

  ngOnInit(): void {
    this.fetchEmployees();
  }

  async fetchEmployees(page=0,pageSize=5) {
    var data: any = await this.be.getAllEmployees(page,pageSize);
    console.log(data)
    this.employees = data.data.content;
    this.length=data.data.totalElements;
  }

  edit(id) {
    this.router.navigateByUrl(`/employees/${id}`)
  }

  async delete(id) {
    await this.be.deleteEmployee(id);
    this.employees.filter((emp,i)=>{
      if(emp.employee_id==id){
        this.employees.splice(i,1);
      }
    })
    alert("Employee deleted")
  }

  handlePageEvent(event:PageEvent){
    this.fetchEmployees(event.pageIndex,event.pageSize);
  }

}
