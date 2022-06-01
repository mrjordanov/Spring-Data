package com.example.mvc.models.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCompaniesDTO {

    @XmlElement(name = "company")
    private List<CompaniesDTO> companiesDTOList;

    public ImportCompaniesDTO() {
    }

    public List<CompaniesDTO> getCompaniesDTOList() {
        return companiesDTOList;
    }
}
