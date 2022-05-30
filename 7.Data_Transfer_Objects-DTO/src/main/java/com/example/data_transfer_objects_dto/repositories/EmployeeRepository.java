package com.example.data_transfer_objects_dto.repositories;

import com.example.data_transfer_objects_dto.entities.Employee;
import com.example.data_transfer_objects_dto.entities.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Set<EmployeeDTO> findByBirthDayAfterOrderBySalaryDesc(LocalDate birthDay);
}
