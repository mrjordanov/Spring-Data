package com.example.json_dto_ex_two.repositories;

import com.example.json_dto_ex_two.entities.Car;
import com.example.json_dto_ex_two.entities.dtoToQueries.CarOutPutDTO;
import com.example.json_dto_ex_two.entities.dtoToQueries.CarOutPutTWODTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

    @Query("select new com.example.json_dto_ex_two.entities.dtoToQueries.CarOutPutDTO(" +
            "c.id ,c.make,c.model,c.travelledDistance) from Car c where c.make=:make ORDER BY c.model asc ,c.travelledDistance desc")
    List<CarOutPutDTO> findAllByMakeEqualsOrderByModelAscTravelledDistanceDesc(String make);

}
