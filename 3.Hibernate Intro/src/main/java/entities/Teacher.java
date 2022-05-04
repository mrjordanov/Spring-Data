package entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="start_work_date")
    private LocalDate startedAt;

    public Teacher() {
    }

    public Teacher(String name, LocalDate startedAt) {
        this.name = name;
        this.startedAt = startedAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartedAt() {
        return startedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartedAt(LocalDate startedAt) {
        this.startedAt = startedAt;
    }
}
