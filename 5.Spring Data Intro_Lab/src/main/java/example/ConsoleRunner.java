package example;

import example.models.Account;
import example.models.User;
import example.services.AccountService;
import example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... strings) throws Exception {
        User user = new User("qna", 22);

        Account account = new Account(BigDecimal.valueOf(50));
        user.setAccounts(account);
        userService.registerUser(user);

        accountService.withdrawMoney(BigDecimal.valueOf(10), account.getId());

    }
}