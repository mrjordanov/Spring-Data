package com.example.json_dto_ex_two.entities.dtoToSeed;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
public class SupplierImportDTO {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public SupplierImportDTO() {
    }

    public String getName() {
        return name;
    }

    public boolean isImporter() {
        return isImporter;
    }
}
