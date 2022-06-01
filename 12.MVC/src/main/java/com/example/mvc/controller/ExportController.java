package com.example.mvc.controller;

import com.example.mvc.models.dto.ExportEmployeeDTO;
import com.example.mvc.service.EmployeeService;
import com.example.mvc.service.ProjectService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExportController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;
    private final Gson gson;

    @Autowired
    public ExportController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping("export/project-if-finished")
    public ModelAndView showFinishedProjects() {
        ModelAndView modelAndView = new ModelAndView("export/export-project-if-finished");

        String result = projectService.getFinishedProjects();

        modelAndView.addObject("projectsIfFinished", result);

        return modelAndView;
    }

    @GetMapping("/export/employees-above")
    public ModelAndView getEmployeesWithAgeAbove() {
        ModelAndView modelAndView = new ModelAndView("export/export-employees-with-age");

        List<ExportEmployeeDTO> employees = employeeService.getEmployeesWithAgeAbove();

        StringBuilder sb = new StringBuilder();

        gson.toJson(employees, sb);

        modelAndView.addObject("employeesAbove", sb.toString());

        return modelAndView;
    }


}
