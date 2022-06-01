package com.example.mvc.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class RegistrationDTO {

    @Size(min = 2, message = "Username must be at least 2 symbols long.")
    private String userName;

    @Size(min = 6)
    private String password;

    private String confirmPassword;

    @Email
    private String email;

    public RegistrationDTO() {
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
