package com.example.mvc.models.dto;

import com.example.mvc.models.Employee;

public class ExportEmployeeDTO {

    private String fullName;

    private int age;

    private String projectName;

    public ExportEmployeeDTO(Employee employee) {
        this.fullName = employee.getFirstName() + " " + employee.getLastName();
        this.age= employee.getAge();
        this.projectName=employee.getProject().getName();
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getProjectName() {
        return projectName;
    }
}
