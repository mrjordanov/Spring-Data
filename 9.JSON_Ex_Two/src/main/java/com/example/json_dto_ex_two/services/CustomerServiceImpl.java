package com.example.json_dto_ex_two.services;

import com.example.json_dto_ex_two.entities.Customer;
import com.example.json_dto_ex_two.entities.dtoToQueries.CustomerFiveDTO;
import com.example.json_dto_ex_two.entities.dtoToQueries.CustomerOneDTO;
import com.example.json_dto_ex_two.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.mapper = new ModelMapper();
    }


    @Override
    public List<CustomerOneDTO> getAllCustomersOrderedByBirthDate() {

        return customerRepository.getAllCustomersOrderByBirthDate().stream().sorted((c1, c2) -> {
                    int result = c1.getBirthDate().compareTo(c2.getBirthDate());
                    if (result == 0) {
                        result = Boolean.compare(!c2.isYoungDriver(), !c1.isYoungDriver());
                    }
                    return result;
                }
        ).toList();

    }

    @Override
    @Transactional
    public List<CustomerFiveDTO> getAllCustomersWithNumberOfBoughtCarsAndTheirPrice() {
       // List<CustomerFiveDTO> collect = customerRepository.getAllCustomersWithTheirCarsAndPrice();
        return null;
    }
}
