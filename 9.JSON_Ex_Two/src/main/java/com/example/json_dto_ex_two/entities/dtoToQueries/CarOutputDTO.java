package com.example.json_dto_ex_two.entities.dtoToQueries;

import java.math.BigInteger;

public class CarOutputDTO {

    private int Id;

    private String Make;

    private String Model;

    private BigInteger TravelledDistance;

    public void setModel(String model) {
        Model = model;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setMake(String make) {
        Make = make;
    }

    public void setTravelledDistance(BigInteger travelledDistance) {
        TravelledDistance = travelledDistance;
    }
}
