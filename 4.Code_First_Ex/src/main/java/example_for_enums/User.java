package example_for_enums;

import javax.persistence.*;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String name;


    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    public User() {
    }

    public User(String name, AccountType accountType) {
        this.name = name;
        this.accountType = accountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
