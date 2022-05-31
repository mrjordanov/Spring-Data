package com.example.json_dto_ex_two.services;
import com.example.json_dto_ex_two.entities.dtoToQueries.CarOutPutTwoDTO;
import com.example.json_dto_ex_two.entities.dtoToQueries.CarOutputDTO;

import java.util.List;

public interface CarService {
    List<CarOutputDTO> getCarsFromToyota();

    List<CarOutPutTwoDTO> carsWithTheirParts();
}
