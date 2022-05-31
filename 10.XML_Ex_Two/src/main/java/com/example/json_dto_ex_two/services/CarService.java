package com.example.json_dto_ex_two.services;


import com.example.json_dto_ex_two.entities.dtoToQueries.CarsOutPutForFourthQuery;
import com.example.json_dto_ex_two.entities.dtoToQueries.CarsOutPutForSecondQueryDTO;

public interface CarService {

    CarsOutPutForSecondQueryDTO getAllCarsForSecondQuery();

    CarsOutPutForFourthQuery getAllCarsForFourthQuery();
}
