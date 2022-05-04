package entities;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

import java.time.LocalDate;

@Entity(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private int age;

    @Column(name = "registration_date")
    private LocalDate registration;

    @Column(name = "last_login")
    private LocalDate lastLogIn;

    public User() {
    }

    public User(String username, int age, LocalDate registration) {
        this.username = username;
        this.age = age;
        this.registration = registration;
        this.lastLogIn = LocalDate.now();
    }

    public LocalDate getLastLogIn() {
        return lastLogIn;
    }

    public void setLastLogIn(LocalDate lastLogIn) {
        this.lastLogIn = lastLogIn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDate registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", registration=" + registration +
                ", lastLogIn=" + lastLogIn +
                '}';
    }
}
