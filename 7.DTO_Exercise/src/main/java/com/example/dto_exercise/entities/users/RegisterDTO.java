package com.example.dto_exercise.entities.users;

/*
 *Validates the data for registering a user
 * Email must be...
 * Pass must be...
 * commandParts[0] is skipped because this is the command name
 * @param commandParts all data read from the console
 * */


import com.example.dto_exercise.exceptions.ValidationException;
import com.sun.xml.bind.v2.TODO;

public class RegisterDTO {
    private String fullName;

    private String email;

    private String password;

    private String confirmPassword;


    public RegisterDTO(String[] commandParts) {
        this.email = commandParts[1];
        this.password = commandParts[2];
        this.confirmPassword = commandParts[3];
        this.fullName = commandParts[4];

        this.validate();
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    private void validate() {
        int indexOfAt = email.indexOf("@");
        int indexOfDot = email.lastIndexOf(".");

        if (indexOfAt < 0 || indexOfDot < 0 || indexOfAt > indexOfDot) {
            throw new ValidationException("Email must contain @ and .");
        }
            //TODO: Validate password
        if(!password.equals(confirmPassword)){
            throw new ValidationException("Password and confirm password must match!");
        }


    }
}
