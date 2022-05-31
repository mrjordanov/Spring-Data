package com.example.json_dto_ex_two.services;


import com.example.json_dto_ex_two.entities.dtoToQueries.CarOutPutDTO;
import com.example.json_dto_ex_two.entities.dtoToQueries.CarOutPutTWODTO;
import com.example.json_dto_ex_two.entities.dtoToQueries.CarsOutPutForFourthQuery;
import com.example.json_dto_ex_two.entities.dtoToQueries.CarsOutPutForSecondQueryDTO;
import com.example.json_dto_ex_two.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper mapper;


    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.mapper=new ModelMapper();
    }

    @Override
    public CarsOutPutForSecondQueryDTO getAllCarsForSecondQuery() {
        List<CarOutPutDTO> cars = carRepository.findAllByMakeEqualsOrderByModelAscTravelledDistanceDesc("Toyota");

        return new CarsOutPutForSecondQueryDTO(cars);
    }

    @Override
    @Transactional
    public CarsOutPutForFourthQuery getAllCarsForFourthQuery() {
        List<CarOutPutTWODTO> collect = carRepository.findAll().stream().map(car -> mapper.map(car, CarOutPutTWODTO.class)).toList();

        return new CarsOutPutForFourthQuery(collect);
    }
}
