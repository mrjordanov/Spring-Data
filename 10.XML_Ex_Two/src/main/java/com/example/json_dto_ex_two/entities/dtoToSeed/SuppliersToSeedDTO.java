package com.example.json_dto_ex_two.entities.dtoToSeed;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersToSeedDTO {

    @XmlElement(name = "supplier")
    private List<SupplierImportDTO> supplierImportDTOList;

    public SuppliersToSeedDTO() {
    }

    public List<SupplierImportDTO> getSupplierImportDTOList() {
        return supplierImportDTOList;
    }
}
