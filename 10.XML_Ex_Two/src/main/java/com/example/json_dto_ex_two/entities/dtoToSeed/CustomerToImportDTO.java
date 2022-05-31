package com.example.json_dto_ex_two.entities.dtoToSeed;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerToImportDTO {

    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    public CustomerToImportDTO() {
    }

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
