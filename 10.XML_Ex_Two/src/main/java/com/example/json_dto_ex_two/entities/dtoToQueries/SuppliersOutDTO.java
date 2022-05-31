package com.example.json_dto_ex_two.entities.dtoToQueries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersOutDTO {

    @XmlElement(name = "supplier")
    private List<SupplierDTO> supplierDTOList;

    public SuppliersOutDTO() {
    }

    public SuppliersOutDTO(List<SupplierDTO> supplierDTOList) {
        this.supplierDTOList = supplierDTOList;
    }
}
