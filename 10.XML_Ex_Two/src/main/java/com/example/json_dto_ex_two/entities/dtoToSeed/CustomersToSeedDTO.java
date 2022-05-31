package com.example.json_dto_ex_two.entities.dtoToSeed;


import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersToSeedDTO implements Serializable {

    @XmlElement(name = "customer")
    private List<CustomerToImportDTO> customerToImportDTOList;


    public CustomersToSeedDTO() {
    }

    public List<CustomerToImportDTO> getCustomerToImportDTOList() {
        return customerToImportDTOList;
    }
}
