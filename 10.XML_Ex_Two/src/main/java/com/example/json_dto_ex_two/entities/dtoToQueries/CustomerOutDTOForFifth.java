package com.example.json_dto_ex_two.entities.dtoToQueries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerOutDTOForFifth {

    @XmlElement(name = "customer")
    private List<CustomersForFifthQuery> customersForFifthQueryList;

    public CustomerOutDTOForFifth() {
    }

    public CustomerOutDTOForFifth(List<CustomersForFifthQuery> customersForFifthQueryList) {
        this.customersForFifthQueryList = customersForFifthQueryList;
    }
}
