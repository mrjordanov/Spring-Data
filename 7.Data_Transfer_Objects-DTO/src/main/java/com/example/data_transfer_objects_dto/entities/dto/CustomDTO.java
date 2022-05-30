package com.example.data_transfer_objects_dto.entities.dto;

public class CustomDTO {
    private String firstName;
    private String lastName;
    private int managerLastNameLength;


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setManagerLastNameLength(int managerLastNameLength) {
        this.managerLastNameLength = managerLastNameLength;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", managerLastNameLength=" + managerLastNameLength +
                '}';
    }
}
