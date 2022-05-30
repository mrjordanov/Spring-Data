package com.example.data_transfer_objects_dto.entities.dto;



import java.math.BigDecimal;

public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String managerLastName;



    public EmployeeDTO(String firstName, String lastName, BigDecimal salary, String managerLastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerLastName = managerLastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    @Override
    public String toString() {
        return firstName +" "+ lastName + " "
                + salary +" - "+
                "Manager: "+(managerLastName==null? "[no manager]" :managerLastName);
    }
}
