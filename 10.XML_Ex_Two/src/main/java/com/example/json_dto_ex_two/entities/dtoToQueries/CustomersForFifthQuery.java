package com.example.json_dto_ex_two.entities.dtoToQueries;

import com.example.json_dto_ex_two.entities.Customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersForFifthQuery {

    @XmlAttribute(name = "full-name")
    private String name;

    @XmlAttribute(name = "bought-cars")
    private int boughtCars;

    @XmlAttribute(name = "spent-money")
    private Double boughtCarsSum;

    public CustomersForFifthQuery() {
    }


    public CustomersForFifthQuery(String name, int boughtCars, Double boughtCarsSum) {
        this.name = name;
        this.boughtCars = boughtCars;
        this.boughtCarsSum = boughtCarsSum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(int boughtCars) {
        this.boughtCars = boughtCars;
    }

    public Double getBoughtCarsSum() {
        return boughtCarsSum;
    }

    public void setBoughtCarsSum(Double boughtCarsSum) {
        this.boughtCarsSum = boughtCarsSum;
    }
}
