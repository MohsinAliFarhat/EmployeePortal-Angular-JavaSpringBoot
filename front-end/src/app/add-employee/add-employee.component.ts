import { Component, OnInit } from '@angular/core';
import { Employee } from '../models/employee.model';
import { BackendCommunicationService } from '../services/backend-communication.service';
@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {
  EMPLOYEE = new Employee('', '', '', '', '', new Date(), 1, '', '');
  constructor(private be:BackendCommunicationService) { }

  ngOnInit(): void {
  }

  async addEmployee(form) {

    if (form.valid && this.EMPLOYEE.salary > 0) {
      form.value.hire_date=new Date(form.value.hire_date).toISOString().split('T')[0].replace("-","/").replace("-","/")
      console.log(form.value)
      await this.be.addEmployee(form.value);
      alert("Employee Added to database")
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
