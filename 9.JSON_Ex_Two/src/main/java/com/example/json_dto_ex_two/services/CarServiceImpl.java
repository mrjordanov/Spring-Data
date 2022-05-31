package com.example.json_dto_ex_two.services;

import com.example.json_dto_ex_two.entities.Car;
import com.example.json_dto_ex_two.entities.dtoToQueries.CarOutPutTwoDTO;
import com.example.json_dto_ex_two.entities.dtoToQueries.CarOutputDTO;
import com.example.json_dto_ex_two.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final ModelMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public List<CarOutputDTO> getCarsFromToyota() {

        List<Car> allToyotaCars = carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        List<CarOutputDTO> result = allToyotaCars.stream().map(car -> mapper.map(car, CarOutputDTO.class)).collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public List<CarOutPutTwoDTO> carsWithTheirParts() {
        return carRepository.findAll()
                .stream()
                .map(car -> mapper.map(car, CarOutPutTwoDTO.class)).collect(Collectors.toList());
    }
}
