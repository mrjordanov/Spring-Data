package com.example.json_dto_ex_two.entities.dtoToSeed;

import java.math.BigInteger;

public class CarsToSeedDTO {

    private String make;

    private String model;

    private BigInteger travelledDistance;


    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public BigInteger getTravelledDistance() {
        return travelledDistance;
    }
}
