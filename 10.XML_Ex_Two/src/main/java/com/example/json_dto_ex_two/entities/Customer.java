package com.example.json_dto_ex_two.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "birth_day")
    private String birthDate;

    @Column(name = "is_young_driver")
    private boolean isYoungDriver;

    @OneToMany(mappedBy = "customer", targetEntity = Sale.class)
    private Set<Sale> boughtCars;

    public Customer() {
        this.boughtCars = new HashSet<>();
    }

    public Customer(String name, String birthDate, boolean isYoungDriver) {
        this();
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDay) {
        this.birthDate = birthDay;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<Sale> getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Set<Sale> boughtCars) {
        this.boughtCars = boughtCars;
    }


    public Double getCostOfCar() {
        Set<Sale> boughtCars = this.boughtCars;
        double totalSum = 0;
        for (Sale boughtCar : boughtCars) {
            Set<Part> part = boughtCar.getCar().getPart();
            double sum = part.stream().mapToDouble(p -> p.getPrice().doubleValue()).sum();
            totalSum += sum;
        }

        return  totalSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
