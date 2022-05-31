package com.example.json_dto_ex_two.services;

import com.example.json_dto_ex_two.entities.Sale;
import com.example.json_dto_ex_two.entities.dtoToQueries.SaleOutDTO;
import com.example.json_dto_ex_two.entities.dtoToQueries.SalesOutDTO;
import com.example.json_dto_ex_two.repositories.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ModelMapper mapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
        this.mapper=new ModelMapper();
    }

    @Override
    @Transactional
    public SalesOutDTO salesWithAppliedDiscount() {
        List<SaleOutDTO> collect = saleRepository.findAll().stream()
                .map(sale -> mapper.map(sale, SaleOutDTO.class))
                .collect(Collectors.toList());

        return new SalesOutDTO(collect);
    }
}
