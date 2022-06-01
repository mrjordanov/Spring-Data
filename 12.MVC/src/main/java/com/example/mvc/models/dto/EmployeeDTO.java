package com.example.mvc.models.dto;



import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeDTO {

    @XmlElement(name = "first-name")
    private String firstName;

    @XmlElement(name = "last-name")
    private String lastName;

    @XmlElement
    @Min(18)
    private int age;

    @XmlElement
    private ProjectImportDTO project;


    public EmployeeDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public ProjectImportDTO getProject() {
        return project;
    }
}
