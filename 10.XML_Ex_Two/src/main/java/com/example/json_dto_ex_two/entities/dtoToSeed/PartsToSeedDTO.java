package com.example.json_dto_ex_two.entities.dtoToSeed;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsToSeedDTO {

    @XmlElement(name = "part")
    private List<PartToImportDTO> partToImportDTOList;


    public PartsToSeedDTO() {
    }

    public List<PartToImportDTO> getPartToImportDTOList() {
        return partToImportDTOList;
    }
}
