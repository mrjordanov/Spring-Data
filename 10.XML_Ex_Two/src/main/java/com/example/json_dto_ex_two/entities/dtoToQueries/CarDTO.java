package com.example.json_dto_ex_two.entities.dtoToQueries;

import com.example.json_dto_ex_two.entities.Part;

import javax.xml.bind.annotation.*;
import java.util.Set;


@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarDTO {

    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private long travelledDistance;


    public CarDTO() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }


}
