package com.example.json_dto_ex_two.entities.dtoToSeed;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
public class CarImportDTO {

    @XmlElement
    private String make;

    @XmlElement
    private String model;

    @XmlElement(name ="travelled-distance")
    private long travelledDistance;

    public CarImportDTO() {
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }
}
