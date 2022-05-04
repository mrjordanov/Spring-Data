package _01;


import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCompleted;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;

    @Column(name = "magic_wand_size")
    private float magicWandSize;

    @Column(name = "deposit_group", length = 20)
    private String depositGroup;

    @Column(name = "deposit_start_date", nullable = false)
    private LocalDateTime depositStartDate;

    @Column(name = "deposit_amount", nullable = false)
    private float depositAmount;

    @Column(name = "deposit_interest", nullable = false)
    private float depositInterest;

    @Column(name = "deposit_charge", nullable = false)
    private float depositCharge;

    @Column(name = "deposit_expiration_date")
    private LocalDateTime depositExpirationDate;

    @Column(name = "is_deposit_expired")
    private boolean depositExpired;

    public WizardDeposits() {
    }

    public WizardDeposits(String firstName, String lastName, String notes, int age, String magicWandCreator, float magicWandSize, String depositGroup, float depositAmount, float depositInterest, float depositCharge, boolean depositExpired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
        this.age = age;
        this.magicWandCreator = magicWandCreator;
        this.magicWandSize = magicWandSize;
        this.depositGroup = depositGroup;
        this.depositStartDate = LocalDateTime.now();
        this.depositAmount = depositAmount;
        this.depositInterest = depositInterest;
        this.depositCharge = depositCharge;
        this.depositExpirationDate = LocalDateTime.now();
        this.depositExpired = depositExpired;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMagicWandCreator() {
        return magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    public float getMagicWandSize() {
        return magicWandSize;
    }

    public void setMagicWandSize(short magicWandSize) {
        this.magicWandSize = magicWandSize;
    }

    public String getDepositGroup() {
        return depositGroup;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    public LocalDateTime getDepositStartDate() {
        return depositStartDate;
    }

    public void setDepositStartDate(LocalDateTime depositStartDate) {
        this.depositStartDate = depositStartDate;
    }

    public float getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(float depositAmount) {
        this.depositAmount = depositAmount;
    }

    public float getDepositInterest() {
        return depositInterest;
    }

    public void setDepositInterest(float depositInterest) {
        this.depositInterest = depositInterest;
    }

    public float getDepositCharge() {
        return depositCharge;
    }

    public void setDepositCharge(float depositCharge) {
        this.depositCharge = depositCharge;
    }

    public LocalDateTime getDepositExpirationDate() {
        return depositExpirationDate;
    }

    public void setDepositExpirationDate(LocalDateTime depositExpirationDate) {
        this.depositExpirationDate = depositExpirationDate;
    }

    public boolean isDepositExpired() {
        return depositExpired;
    }

    public void setDepositExpired(boolean depositExpired) {
        this.depositExpired = depositExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WizardDeposits that = (WizardDeposits) o;
        return id == that.id && firstName.equals(that.firstName) && lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
