/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author mohsi
 */
@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "user_sequence"),
                @Parameter(name = "initial_value", value = "4"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    private int employee_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date hire_date;
    private int salary;
    private int manager_id;
    private int department_id;

    public void update(Employee employee) {
        if (employee.getFirst_name() != null) {
            first_name = employee.getFirst_name();
        }
        if (employee.getLast_name() != null) {
            last_name = employee.getLast_name();
        }
        if (employee.getEmail() != null) {
            email = employee.getEmail();
        }
        if (employee.getPhone_number() != null) {
            phone_number = employee.getPhone_number();
        }
        if (employee.getHire_date() != null) {
            hire_date = employee.getHire_date();
        }
        if (employee.getSalary() >= 0) {
            salary = employee.getSalary();
        }
        manager_id = employee.getManager_id();
        department_id = employee.getDepartment_id();
    }

}
