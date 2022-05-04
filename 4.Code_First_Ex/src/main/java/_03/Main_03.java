package _03;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class Main_03 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = entityManagerFactory.createEntityManager();



        entityManager.getTransaction().begin();


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
