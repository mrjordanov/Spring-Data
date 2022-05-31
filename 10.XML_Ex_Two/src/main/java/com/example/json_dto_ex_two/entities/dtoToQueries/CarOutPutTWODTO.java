package com.example.json_dto_ex_two.entities.dtoToQueries;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarOutPutTWODTO {

    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private long travelledDistance;

    @XmlElementWrapper(name = "parts")
    private Set<PartsOutPutDTO> part;

    public CarOutPutTWODTO() {
    }

  /*  public CarOutPutTWODTO(String make, String model, long travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.part =new HashSet<>();
    }*/

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public void setPart(Set<PartsOutPutDTO> part) {
        this.part = part;
    }
}
