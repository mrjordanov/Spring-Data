package com.example.mvc.controller;

import com.example.mvc.service.CompanyService;
import com.example.mvc.service.EmployeeService;
import com.example.mvc.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class ImportController {

    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @Autowired
    public ImportController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/import")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("xml/import-xml");
        boolean[] areImported = new boolean[3];
        areImported[0] = companyService.areImported();
        areImported[1] = projectService.areImported();
        areImported[2] = employeeService.areImported();

        modelAndView.addObject("areImported", areImported);

        return modelAndView;
    }


    @GetMapping("import/companies")
    public ModelAndView showImportCompanies() throws IOException {
        ModelAndView modelAndView = new ModelAndView("xml/import-companies");

        String companies = companyService.getCompaniesText();

        modelAndView.addObject("companies", companies);

        return modelAndView;
    }

    @PostMapping("import/companies")
    public String importCompanies() throws JAXBException, FileNotFoundException {
        String result = companyService.importCompanies();
        System.out.println(result);

        return "redirect:/import";
    }

    @GetMapping("import/projects")
    public ModelAndView showImportProjects() throws IOException {
        ModelAndView modelAndView = new ModelAndView("xml/import-projects");

        String projects = projectService.getProjectsText();

        modelAndView.addObject("projects", projects);

        return modelAndView;
    }

    @PostMapping("import/projects")
    public String importProjects() throws JAXBException, FileNotFoundException {
        String result = projectService.importProjects();
        System.out.println(result);

        return "redirect:/import";
    }

    @GetMapping("import/employees")
    public ModelAndView showImportEmployees() throws IOException {
        ModelAndView modelAndView = new ModelAndView("xml/import-employees");

        String employees = employeeService.getEmployeesText();

        modelAndView.addObject("employees", employees);

        return modelAndView;
    }

    @PostMapping("import/employees")
    public String importEmployees() throws JAXBException, FileNotFoundException {
        String result = employeeService.importEmployees();
        System.out.println(result);

        return "redirect:/import";
    }











}
