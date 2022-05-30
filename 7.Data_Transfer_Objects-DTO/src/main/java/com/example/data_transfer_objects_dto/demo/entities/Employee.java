package com.example.data_transfer_objects_dto.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate birthDay;
    private Address address;
    private boolean onVacation;
    private Employee manager;
    private Set<Employee> subordinates;

    public void setOnVacation(boolean onVacation) {
        this.onVacation = onVacation;
    }

   public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void setSubordinates(Set<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    public boolean isOnVacation() {
        return onVacation;
    }

    public Employee getManager() {
        return manager;
    }

    public Set<Employee> getSubordinates() {
        return subordinates;
    }

    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate birthDay, Address address, boolean onVacation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthDay = birthDay;
        this.address = address;
        this.onVacation = onVacation;
        this.subordinates = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addEmployee(Employee employee) {
        this.subordinates.add(employee);
    }

}
