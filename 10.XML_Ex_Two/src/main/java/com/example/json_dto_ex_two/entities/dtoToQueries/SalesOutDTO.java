package com.example.json_dto_ex_two.entities.dtoToQueries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesOutDTO {

    @XmlElement(name = "sale")
    private List<SaleOutDTO> salesOutDTOList;

    public SalesOutDTO() {
    }

    public SalesOutDTO(List<SaleOutDTO> salesOutDTOList) {
        this.salesOutDTOList = salesOutDTOList;
    }
}
