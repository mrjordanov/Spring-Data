package com.example.json_dto_ex_two.entities.dtoToQueries;


import javax.xml.bind.annotation.*;


@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleOutDTO {

    @XmlElement(name = "car")
    private CarDTO car;

    @XmlElement(name = "customer-name")
    private String customerName;

    @XmlElement
    private double discount;


    public SaleOutDTO() {
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public CarDTO getCar() {
        return car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getDiscount() {
        return discount;
    }
}
