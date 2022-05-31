package com.example.json_dto_ex_two.entities.dtoToQueries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersForFirstQuery {

    @XmlElement(name = "customer")
    private List<CustomerOutPutForFirstQuery> customerOutPutForFirstQueryList;

    public CustomersForFirstQuery() {
    }

    public CustomersForFirstQuery(List<CustomerOutPutForFirstQuery> customerOutPutForFirstQueryList) {
        this.customerOutPutForFirstQueryList = customerOutPutForFirstQueryList;
    }
}
