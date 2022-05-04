package _03;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "students")
public class Student extends Person {

    @Column(name = "avverage_grade",nullable = false)
    private double avvGrade;

    private int attendance;

    @ManyToMany
    @JoinTable(name = "students_courses",
    joinColumns = @JoinColumn(name="student_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"))
    private Set<Course> courses;


    public Student() {
      //  super();
    }

    public Student(String firstName, String lastName, String phoneNumber, double avvGrade, int attendance) {
        super(firstName, lastName, phoneNumber);
        this.avvGrade = avvGrade;
        this.attendance = attendance;
        this.courses=new HashSet<>();
    }

    public double getAvvGrade() {
        return avvGrade;
    }

    public void setAvvGrade(double avvGrade) {
        this.avvGrade = avvGrade;
    }

    public int isAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
