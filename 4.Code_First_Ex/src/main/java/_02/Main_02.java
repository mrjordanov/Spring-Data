package _02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main_02 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Product product= new Product("Nivea",5, BigDecimal.ZERO);
        Customer customer= new Customer("Marin","@abv","8802");
        StoreLocation storeLocation=new StoreLocation("Haskovo");
        Sale sale= new Sale(product,customer,storeLocation);


        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.persist(customer);
        entityManager.persist(storeLocation);
        entityManager.persist(sale);


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
