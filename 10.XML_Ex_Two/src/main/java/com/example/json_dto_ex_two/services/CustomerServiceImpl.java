package com.example.json_dto_ex_two.services;

import com.example.json_dto_ex_two.entities.Customer;
import com.example.json_dto_ex_two.entities.dtoToQueries.CustomerOutDTOForFifth;
import com.example.json_dto_ex_two.entities.dtoToQueries.CustomerOutPutForFirstQuery;
import com.example.json_dto_ex_two.entities.dtoToQueries.CustomersForFifthQuery;
import com.example.json_dto_ex_two.entities.dtoToQueries.CustomersForFirstQuery;
import com.example.json_dto_ex_two.repositories.CustomerRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;
    private final TypeMap<Customer, CustomersForFifthQuery> customerCustomersForFifthQueryTypeMap;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.mapper = new ModelMapper();

        Converter<Customer, Double> userSpendMoney =
                context -> context.getSource() == null ? null : context.getSource().getCostOfCar();

        TypeMap<Customer, CustomersForFifthQuery> typeMap = this.mapper.createTypeMap(Customer.class, CustomersForFifthQuery.class);
        this.customerCustomersForFifthQueryTypeMap = typeMap.addMappings(map ->
                map.using(userSpendMoney).map(Customer::getCostOfCar, CustomersForFifthQuery::setBoughtCarsSum));
        this.mapper.addConverter(userSpendMoney);
    }


    @Override
    public CustomersForFirstQuery getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        List<CustomerOutPutForFirstQuery> collect = allCustomers.stream().sorted((c1, c2) -> {
            int result = c1.getBirthDate().compareTo(c2.getBirthDate());
            if (result == 0) {
                result = Boolean.compare(!c2.isYoungDriver(), !c1.isYoungDriver());
            }
            return result;
        }).map(customer -> mapper.map(customer, CustomerOutPutForFirstQuery.class)).toList();

        return new CustomersForFirstQuery(collect);
    }

    @Override
    @Transactional
    public CustomerOutDTOForFifth getAllCustomersForFifthQuery() {
        List<Customer> allCustomersWithTheirCars = customerRepository.findAll();

        List<CustomersForFifthQuery> collect = allCustomersWithTheirCars.stream().map(this.customerCustomersForFifthQueryTypeMap::map).collect(Collectors.toList());

        return new CustomerOutDTOForFifth(collect);
    }
}
