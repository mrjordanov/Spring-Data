package com.example.json_dto_ex_two.entities.dtoToQueries;

import java.util.HashSet;
import java.util.Set;

public class CustomerOneDTO {

    private int Id;

    private String Name;

    private String BirthDate;

    private boolean IsYoungDriver;

  //  private Set<SaleOneDTO> Sales;


    public CustomerOneDTO(int id, String name, String birthDate, boolean isYoungDriver) {
        Id = id;
        Name = name;
        BirthDate = birthDate;
        IsYoungDriver = isYoungDriver;
     //   this.Sales=new HashSet<>();
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

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return IsYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        IsYoungDriver = youngDriver;
    }

 /*   public Set<SaleOneDTO> getSales() {
        return Sales;
    }

    public void setSales(Set<SaleOneDTO> sales) {
        Sales = sales;
    }*/
}
