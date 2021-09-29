import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Employee } from '../models/employee.model';
import { BackendCommunicationService } from '../services/backend-communication.service';
@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})
export class EditEmployeeComponent implements OnInit {

  EMPLOYEE = new Employee('', '', '', '', '', new Date(), 1, '', '');
  constructor(private be: BackendCommunicationService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.url.subscribe(params => {
      this.showSingleEmpDetails(params[1].path)
    });
  }

  async showSingleEmpDetails(id) {
    var data: any = await this.be.getSingleEmployee(id);
    var date1 = new Date(data.data.hire_date);
    var date2 = new Date(data.data.hire_date);
    date2.setDate(date1.getDate() + 1);
    data = data.data;
    this.EMPLOYEE = new Employee(
      data.employee_id,
      data.first_name,
      data.last_name,
      data.email,
      data.phone_number,
      date2.toISOString().split('T')[0],
      data.salary,
      data.manager_id,
      data.department_id)
  }

  async updateEmployee(form) {

    if (form.valid && this.EMPLOYEE.salary > 0) {
      form.value.hire_date = new Date(form.value.hire_date).toISOString().split('T')[0].replace("-", "/").replace("-", "/")
      await this.be.updateEmployee(this.EMPLOYEE.id, form.value);
      alert("Employee updated in database")
    }

  }
  phoneNumberValidation(event) {
    const input = String.fromCharCode(event.keyCode);
    if (/[0-9-]/.test(input)) {
      return true;
    } else {
      event.preventDefault();
      return false;
    }
  }
}
