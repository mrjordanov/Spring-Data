package com.example.json_dto_ex_two.entities.dtoToSeed;


import java.io.Serializable;


public class CustomerToSeedDTO implements Serializable {

    private String name;

    private String birthDate;

    private boolean isYoungDriver;


    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }
}
