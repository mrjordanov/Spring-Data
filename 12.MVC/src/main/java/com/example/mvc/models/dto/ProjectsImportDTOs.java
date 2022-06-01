package com.example.mvc.models.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectsImportDTOs {

    @XmlElement(name = "project")
    private List<ProjectImportDTO> projectImportDTOList;

    public ProjectsImportDTOs() {
    }

    public List<ProjectImportDTO> getProjectImportDTOList() {
        return projectImportDTOList;
    }
}
