package com.example.json_dto_ex_two.entities.dtoToQueries;

import java.math.BigDecimal;

public class CustomerFiveDTO {

    private String fullName;

    private int boughtCarsCount;

    private BigDecimal spentMoney;


    public CustomerFiveDTO(String fullName, int boughtCarsCount, BigDecimal spentMoney) {
        this.fullName = fullName;
        this.boughtCarsCount = boughtCarsCount;
        this.spentMoney = spentMoney;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBoughtCarsCount(int boughtCarsCount) {
        this.boughtCarsCount = boughtCarsCount;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
