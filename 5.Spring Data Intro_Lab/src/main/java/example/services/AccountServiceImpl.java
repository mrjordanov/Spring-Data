package example.services;

import example.models.Account;
import example.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Integer id) {
        Account accountById = accountRepository.findAccountById(id);

        if (accountById != null) {
            BigDecimal curBalance = accountById.getBalance();
            BigDecimal diff = curBalance.subtract(money);
            if (diff.compareTo(BigDecimal.ZERO) >= 0) {
                accountById.setBalance(diff);
            } else {
                System.out.println("Not enough money in account");
            }
        } else {
            System.out.println("No such account is present id DB");
        }

    }

    @Override
    public void transferMoney(BigDecimal money, Integer id) {

    }
}
