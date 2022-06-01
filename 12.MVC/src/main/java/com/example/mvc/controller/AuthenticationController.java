package com.example.mvc.controller;

import com.example.mvc.models.dto.RegistrationDTO;
import com.example.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;


@Controller
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String registerView(RegistrationDTO registrationDTO) {
        ModelAndView modelAndView=new ModelAndView("user/register");
        modelAndView.addObject("registrationDTO",registrationDTO);

        return "user/register";
    }

    @PostMapping(value = "users/register")
    public String doRegister(@Valid RegistrationDTO registrationDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "user/register";
        }

        userService.register(registrationDTO);

        return "user/login";
    }

}
