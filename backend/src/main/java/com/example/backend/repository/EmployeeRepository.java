/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend.repository;
import com.example.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author mohsi
 */
public interface EmployeeRepository  extends PagingAndSortingRepository<Employee, Integer>, JpaRepository<Employee, Integer> {
    
}
