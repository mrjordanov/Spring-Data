package com.example.json_dto_ex_two.services;


import com.example.json_dto_ex_two.entities.dtoToQueries.CustomerOutDTOForFifth;
import com.example.json_dto_ex_two.entities.dtoToQueries.CustomersForFirstQuery;

public interface CustomerService {

    CustomersForFirstQuery getAllCustomers();

    CustomerOutDTOForFifth getAllCustomersForFifthQuery();
}
