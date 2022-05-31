package com.example.json_dto_ex_two.entities.dtoToQueries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsOutPutForSecondQueryDTO {

    @XmlElement(name = "car")
    private List<CarOutPutDTO> carOutPutDTOList;

    public CarsOutPutForSecondQueryDTO() {
    }

    public CarsOutPutForSecondQueryDTO(List<CarOutPutDTO> carOutPutDTOList) {
        this.carOutPutDTOList = carOutPutDTOList;
    }
}
