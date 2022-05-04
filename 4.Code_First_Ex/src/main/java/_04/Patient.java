package _04;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String email;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime dateOfBirth;

    @Column(name = "picture_name", nullable = false)
    private String pictureName;

    @Column(name = "medical_insurance_status")
    private boolean haveMedicalInsurance;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, targetEntity = Visitation.class)
    private Set<Visitation> visitation;

    @ManyToMany(mappedBy = "patients")
    private Set<Diagnose> diagnoses;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String email, String pictureName, boolean haveMedicalInsurance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = LocalDateTime.now();
        this.pictureName = pictureName;
        this.haveMedicalInsurance = haveMedicalInsurance;
        this.visitation = new HashSet<>();
        this.diagnoses = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public boolean isHaveMedicalInsurance() {
        return haveMedicalInsurance;
    }

    public void setHaveMedicalInsurance(boolean haveMedicalInsurance) {
        this.haveMedicalInsurance = haveMedicalInsurance;
    }

    public void addVisitation(Visitation visitation) {
        this.visitation.add(visitation);
    }

    public Set<Visitation> getVisitation() {
        return visitation;
    }

    public void setVisitation(Set<Visitation> visitation) {
        this.visitation = visitation;
    }

    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }


}
