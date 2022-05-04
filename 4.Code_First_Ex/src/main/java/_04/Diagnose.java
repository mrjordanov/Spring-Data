package _04;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "diagnoses")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String comments;

    @ManyToMany
    @JoinTable(name = "diagnoses_medicaments",
            joinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"))
    private Set<Patient> patients;

    @OneToMany(mappedBy = "diagnose")
    private Set<Medicament> medicaments;

    public Diagnose() {
    }

    public Diagnose(String name, String comments) {
        this.name = name;
        this.comments = comments;
        this.patients = new HashSet<>();
        this.medicaments = new HashSet<>();
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;


    }
}
