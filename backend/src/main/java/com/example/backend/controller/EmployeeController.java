/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend.controller;

import com.example.backend.model.Employee;
import com.example.backend.repository.EmployeeRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.service.HttpResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mohsi
 */
@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping(path = (""), produces = "application/json")
    public ResponseEntity<HttpResponse> postEmployees(HttpServletRequest request, @RequestBody Employee bodyEmployee) {
        String logprefix = request.getRequestURI();
        HttpResponse response = new HttpResponse(request.getRequestURI());
        response.setData(employeeRepository.save(bodyEmployee));
        response.setStatus(HttpStatus.CREATED);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping(path = (""), produces = "application/json")
    public ResponseEntity<HttpResponse> getEmployees(
            HttpServletRequest request,
            @RequestParam int page,
            @RequestParam int pageSize) {
        String logprefix = request.getRequestURI();
        HttpResponse response = new HttpResponse(request.getRequestURI());
        response.setStatus(HttpStatus.OK);
        Pageable pageable = PageRequest.of(page, pageSize, Direction.ASC, "email");
        response.setData(employeeRepository.findAll(pageable));
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping(path = ("/{id}"), produces = "application/json")
    public ResponseEntity<HttpResponse> getSingleEmployee(
            HttpServletRequest request,
            @PathVariable int id) {
        String logprefix = request.getRequestURI();
        HttpResponse response = new HttpResponse(request.getRequestURI());
        response.setData(employeeRepository.findById(id));
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping(path = ("/{id}"), produces = "application/json")
    public ResponseEntity<HttpResponse> putEmployees(
            HttpServletRequest request,
            @RequestBody Employee bodyEmployee,
            @PathVariable int id) {
        String logprefix = request.getRequestURI();
        HttpResponse response = new HttpResponse(request.getRequestURI());
        Optional<Employee> optEmployee = employeeRepository.findById(id);
        if (optEmployee == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(response.getStatus()).body(response);
        }
        Employee employee = optEmployee.get();
        employee.update(bodyEmployee);
        employeeRepository.save(employee);
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping(path = ("/{id}"), produces = "application/json")
    public ResponseEntity<HttpResponse> deleteEmployees(
            HttpServletRequest request,
            @PathVariable int id) {
        String logprefix = request.getRequestURI();
        HttpResponse response = new HttpResponse(request.getRequestURI());
        employeeRepository.deleteById(id);
        response.setStatus(HttpStatus.OK);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
