package com.example.data_transfer_objects_dto.demo.dto;

import java.util.Set;
import java.util.stream.Collectors;

public class ManagerDTO {
    private String firstName;
    private String lastName;
    private Set<EmployeeDTO> subordinates;

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

    public Set<EmployeeDTO> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Set<EmployeeDTO> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        String employees=subordinates.stream()
                .map(EmployeeDTO::toString).map(s->"   - "+s)
                .collect(Collectors.joining("\n"));

        return String.format("%s %s | Employees: %d%n%s%n",
                firstName,lastName,subordinates.size(),employees);
    }
}
