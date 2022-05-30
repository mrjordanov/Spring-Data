package com.example.data_transfer_objects_dto.services;

import com.example.data_transfer_objects_dto.entities.Employee;
import com.example.data_transfer_objects_dto.entities.dto.EmployeeDTO;
import com.example.data_transfer_objects_dto.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Set<EmployeeDTO> findEmployeesBornBefore(int year) {
        LocalDate localDate=LocalDate.of(year,1,1);
        return employeeRepository.findByBirthDayAfterOrderBySalaryDesc(localDate);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
