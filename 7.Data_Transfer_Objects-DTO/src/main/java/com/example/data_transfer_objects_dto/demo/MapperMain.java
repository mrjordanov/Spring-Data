package com.example.data_transfer_objects_dto.demo;


import com.example.data_transfer_objects_dto.demo.dto.ManagerDTO;
import com.example.data_transfer_objects_dto.demo.entities.Address;
import com.example.data_transfer_objects_dto.demo.entities.Employee;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MapperMain {
    public static void main(String[] args) {
        ModelMapper mapper = new ModelMapper();

        Address address = new Address("Boris 3", 3, "Sofia", "Bulgaria");
        Employee manager = new Employee("Mr.", "Manager", new BigDecimal(10), LocalDate.now(), address, true);
        Employee first = new Employee("Mr.", "Employee First", new BigDecimal(100), LocalDate.now(), address, true);
        Employee second = new Employee("Mr.", "Employee Second", new BigDecimal(10), LocalDate.now(), address, true);

        manager.addEmployee(first);
        manager.addEmployee(second);

        ManagerDTO dto = mapper.map(manager, ManagerDTO.class);

        System.out.println(dto);
    }
}
