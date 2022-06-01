package com.example.mvc.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesROOTDTO {

    @XmlElement(name = "employee")
    private List<EmployeeDTO> employeeDTOList;

    public EmployeesROOTDTO() {
    }

    public List<EmployeeDTO> getEmployeeDTOList() {
        return employeeDTOList;
    }
}
