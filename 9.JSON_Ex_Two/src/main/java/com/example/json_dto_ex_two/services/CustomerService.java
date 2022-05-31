package com.example.json_dto_ex_two.services;

import com.example.json_dto_ex_two.entities.dtoToQueries.CustomerFiveDTO;
import com.example.json_dto_ex_two.entities.dtoToQueries.CustomerOneDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerOneDTO> getAllCustomersOrderedByBirthDate();

    List<CustomerFiveDTO> getAllCustomersWithNumberOfBoughtCarsAndTheirPrice();
}
