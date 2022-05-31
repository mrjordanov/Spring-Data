package com.example.json_dto_ex_two.entities.dtoToQueries;

public class SupplierDTO {

    private int Id;

    private String Name;

    private int partsCount;

    public SupplierDTO(int id, String name, int partsCount) {
        Id = id;
        Name = name;
        this.partsCount = partsCount;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}
