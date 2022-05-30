package com.example.data_transfer_objects_dto.services;

import com.example.data_transfer_objects_dto.entities.Employee;
import com.example.data_transfer_objects_dto.entities.dto.EmployeeDTO;

import java.util.List;
import java.util.Set;


public interface EmployeeService {

    Set<EmployeeDTO> findEmployeesBornBefore(int year);
    void save(Employee employee);

    List<Employee> findAll();
}
