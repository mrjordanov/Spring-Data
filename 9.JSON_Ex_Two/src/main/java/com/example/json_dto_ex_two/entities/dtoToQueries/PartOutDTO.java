package com.example.json_dto_ex_two.entities.dtoToQueries;

import java.math.BigDecimal;

public class PartOutDTO {

    private String Name;

    private BigDecimal Price;


    public void setName(String name) {
        Name = name;
    }


    public void setPrice(BigDecimal price) {
        Price = price;
    }
}
