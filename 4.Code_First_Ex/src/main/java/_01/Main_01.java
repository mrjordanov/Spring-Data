package _01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Main_01 {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        WizardDeposits wizardDeposit = new WizardDeposits("Gandalf", "Beliq", "I`m gandalf", 100, "Prychka", 5, "First", 5000, 1, 5, true);
        entityManager.getTransaction().begin();
        entityManager.persist(wizardDeposit);


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
