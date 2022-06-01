package com.example.mvc.service;

import com.example.mvc.models.Employee;
import com.example.mvc.models.Project;
import com.example.mvc.models.dto.EmployeesROOTDTO;
import com.example.mvc.models.dto.ExportEmployeeDTO;
import com.example.mvc.repositories.EmployeeRepository;
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
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final Path path = Path.of("src/main/resources/files/xmls/employees.xml");
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final MyValidator myValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ProjectRepository projectRepository, MyValidator myValidator, @Qualifier("withLocalDate") ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.myValidator = myValidator;
        this.modelMapper = modelMapper;
    }

    public boolean areImported() {
        return employeeRepository.count() > 0;
    }

    public String getEmployeesText() throws IOException {
        return Files.readString(path);
    }

    public String importEmployees() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        FileReader reader = new FileReader("src\\main\\resources\\files\\xmls\\employees.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(EmployeesROOTDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        EmployeesROOTDTO dtos = (EmployeesROOTDTO) unmarshaller.unmarshal(reader);

        dtos.getEmployeeDTOList().forEach(e -> {

            if (!myValidator.isValid(e)) {
                sb.append("Invalid Employee or Project").append("\n");
            } else {
                Employee employeeToAdd = modelMapper.map(e, Employee.class);

                Optional<Project> project = projectRepository.findByName(e.getProject().getName());


                if (project.isEmpty()) {
                    sb.append("Invalid Project Name").append("\n");
                } else {

                    employeeToAdd.setProject(project.get());
                    employeeRepository.save(employeeToAdd);
                    sb.append("Successfully added ").append(employeeToAdd.getFirstName()).append(" ").append(employeeToAdd.getLastName()).append("\n");
                }
            }

        });


        return sb.toString().trim();
    }

    public List<ExportEmployeeDTO> getEmployeesWithAgeAbove() {

        List<Employee> allEmployees = employeeRepository.getAllByAgeGreaterThan(25);

        return allEmployees.stream()
                .map(ExportEmployeeDTO::new)
                .toList();
    }
}
