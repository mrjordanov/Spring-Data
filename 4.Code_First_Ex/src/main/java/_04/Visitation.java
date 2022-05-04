package _04;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "visitations")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_of_visitation",nullable = false)
    private LocalDateTime dateOfVisitation;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(optional = false)
    private Patient patient;

    public Visitation() {
    }

    public Visitation( String comments) {
        this.dateOfVisitation = LocalDateTime.now();
        this.comments = comments;
    }

    public LocalDateTime getDateOfVisitation() {
        return dateOfVisitation;
    }

    public void setDateOfVisitation(LocalDateTime dateOfVisitation) {
        this.dateOfVisitation = dateOfVisitation;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setId(int id) {
        this.id = id;
    }

}
