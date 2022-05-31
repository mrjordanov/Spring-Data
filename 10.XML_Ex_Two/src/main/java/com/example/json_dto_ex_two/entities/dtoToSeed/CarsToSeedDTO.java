package com.example.json_dto_ex_two.entities.dtoToSeed;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsToSeedDTO {

    @XmlElement(name = "car")
    private List<CarImportDTO> carImportDTOList;

    public CarsToSeedDTO() {
    }

    public List<CarImportDTO> getCarImportDTOList() {
        return carImportDTOList;
    }
}
