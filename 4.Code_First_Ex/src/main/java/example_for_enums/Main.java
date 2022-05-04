package example_for_enums;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user= new User("Marin",AccountType.FREE);
        entityManager.persist(user);



        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
