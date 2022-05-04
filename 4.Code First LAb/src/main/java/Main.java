import lab.inheritance.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Vehicle bike = new Bike("BMX", "S8", 50, "No Fuel");
        Vehicle car = new Car("Audi", "Q6", 5000, "Diesel", 5);
        Vehicle plane = new Plane("Boeing", "737", 5000000, "Cerosine", 185);
        Vehicle truck = new Truck("MAN", "FH", 555555, "Diesel", 50);
        entityManager.getTransaction().begin();


        entityManager.persist(bike);
        entityManager.persist(car);
        entityManager.persist(plane);
        entityManager.persist(truck);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
