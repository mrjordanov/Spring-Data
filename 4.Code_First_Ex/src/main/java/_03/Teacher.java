package _03;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "teachers")
public class Teacher extends Person {

    @Column(nullable = false,unique = true)
    private String email;

    @Column(name = "salary_per_hour",nullable = false)
    private double salaryPerHour;

    @OneToMany (mappedBy = "teacher")
    private Set<Course> course;

    public Teacher() {
       // super();
    }

    public Teacher(String firstName, String lastName, String phoneNumber, String email, double salaryPerHour) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
        this.course=new HashSet<>();
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
