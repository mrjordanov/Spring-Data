package com.example.json_dto_ex_two.entities.dtoToQueries;

import java.math.BigInteger;

import java.util.Set;

public class CarOutPutTwoDTO {

    private String Make;

    private String Model;

    private BigInteger TravelledDistance;

    private Set<PartOutDTO> part;


    public void setMake(String make) {
        Make = make;
    }

    public void setModel(String model) {
        Model = model;
    }

    public void setTravelledDistance(BigInteger travelledDistance) {
        TravelledDistance = travelledDistance;
    }

    public void setPart(Set<PartOutDTO> part) {
        this.part = part;
    }
}
