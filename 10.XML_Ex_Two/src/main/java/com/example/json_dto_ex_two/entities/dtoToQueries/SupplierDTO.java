package com.example.json_dto_ex_two.entities.dtoToQueries;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierDTO {

    @XmlAttribute
    private  int id;
    @XmlAttribute
    private String name;
    @XmlAttribute(name = "parts-count")
    private int parts;

    public SupplierDTO(int id, String name, int parts) {
        this.id = id;
        this.name = name;
        this.parts = parts;
    }

    public SupplierDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParts() {
        return parts;
    }

    public void setParts(int parts) {
        this.parts = parts;
    }
}
