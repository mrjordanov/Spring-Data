package com.example.data_transfer_objects_dto;


import com.example.data_transfer_objects_dto.entities.Employee;
import com.example.data_transfer_objects_dto.entities.dto.CustomDTO;
import com.example.data_transfer_objects_dto.entities.dto.EmployeeDTO;
import com.example.data_transfer_objects_dto.services.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private EmployeeService employeeService;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        // persist();

        employeeService.findEmployeesBornBefore(1990).forEach(System.out::println);
       /* ModelMapper mapper = new ModelMapper();
        managerList.stream().map(e -> mapper.map(e, EmployeeDTO.class)).forEach(System.out::println);*/

        List<Employee> employeeList = employeeService.findAll();
        ModelMapper mapper = new ModelMapper();

        TypeMap<Employee, CustomDTO> employeeToCustomDTO = mapper.createTypeMap(Employee.class, CustomDTO.class);

        Converter<Employee,Integer> getLastNameLength=ctx->ctx.getSource()==null? null: ctx.getSource().getLastName().length();
        employeeToCustomDTO
                .addMappings(mapping-> mapping.when(Objects::nonNull)
                .using(getLastNameLength).map(Employee::getManager,CustomDTO::setManagerLastNameLength));



        employeeList.stream().map(employeeToCustomDTO::map).forEach(System.out::println);
    }

    private void persist() {
        Employee manager = new Employee("Mr.", "Manager", new BigDecimal(1000), LocalDate.now(), null);
        Employee first = new Employee("first", "last", new BigDecimal(10), LocalDate.now(), manager);
        Employee second = new Employee("second", "last", new BigDecimal(10), LocalDate.now(), manager);

        employeeService.save(first);
    }
}
