package com.example.mvc.service;


import com.example.mvc.models.Company;
import com.example.mvc.models.Project;
import com.example.mvc.models.dto.ProjectsImportDTOs;
import com.example.mvc.repositories.CompanyRepository;
import com.example.mvc.repositories.ProjectRepository;
import com.example.mvc.util.MyValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final CompanyRepository companyRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper mapper;
    private final MyValidator myValidator;

    @Autowired
    public ProjectService(CompanyRepository companyRepository, ProjectRepository projectRepository, @Qualifier("withLocalDate") ModelMapper mapper, MyValidator myValidator) {
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
        this.mapper = mapper;
        this.myValidator = myValidator;
    }

    public boolean areImported() {
        return projectRepository.count()>0;
    }

    public String getProjectsText() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xmls/projects.xml"));
    }

    public String importProjects() throws JAXBException, FileNotFoundException {
        FileReader reader = new FileReader("src\\main\\resources\\files\\xmls\\projects.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(ProjectsImportDTOs.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ProjectsImportDTOs dtOs = (ProjectsImportDTOs) unmarshaller.unmarshal(reader);

        StringBuilder sb = new StringBuilder();

        dtOs.getProjectImportDTOList().forEach(p -> {

            if (!myValidator.isValid(p)) {
                sb.append("Invalid project").append("\n");
            }
            Optional<Company> company = companyRepository.findByName(p.getCompany().getName());

            Project project = mapper.map(p, Project.class);
            project.setCompany(company.get());

            projectRepository.save(project);
            sb.append("Created project - ").append(project.getName())
                    .append(" for company ").append(project.getCompany().getName()).append("\n");
        });

        return sb.toString();
    }

    public String getFinishedProjects() {
        StringBuilder sb=new StringBuilder();

        List<Project> allProjects = projectRepository.findAllByIsFinishedTrueOrderByPaymentDesc();
        allProjects.forEach(p->sb.append(p).append("\n"));


        return sb.toString();
    }
}
