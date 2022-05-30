package com.example.dto_exercise;


import com.example.dto_exercise.exceptions.UserNotLoggedException;
import com.example.dto_exercise.exceptions.ValidationException;
import com.example.dto_exercise.services.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ExecutorService executorService;

    @Autowired
    public ConsoleRunner(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("STOP")){
            String result;
            try {
                result = executorService.execute(command);
            } catch (ValidationException | UserNotLoggedException e) {
                result = e.getMessage();
            }

            System.out.println(result);
            command= scanner.nextLine();
        }

    }
}
