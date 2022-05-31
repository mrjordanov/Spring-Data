package com.example.json_dto_ex_two.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private long travelledDistance;

    @ManyToMany
    private Set<Part> part;

    public Car() {
        this.part =new HashSet<>();
    }

    public Car(String make, String model, long travelledDistance) {
        this();
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<Part> getPart() {
        return part;
    }

    public void setPart(Set<Part> parts) {
        this.part = parts;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public double getPriceOfCar(){
        return this.part.stream().mapToDouble(p -> p.getPrice().doubleValue()).sum();
    }

    public void setPriceOfCar(){
        this.getPriceOfCar();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
