package com.example.json_dto_ex_two.entities.dtoToQueries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsOutPutForFourthQuery {

    @XmlElement(name = "car")
    private List<CarOutPutTWODTO> carOutPutTWODTOList;


    public CarsOutPutForFourthQuery() {
    }

    public CarsOutPutForFourthQuery(List<CarOutPutTWODTO> carOutPutTWODTOList) {
        this.carOutPutTWODTOList = carOutPutTWODTOList;
    }
}
