package _04;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;


public class Main_04 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Visitation visitation= new Visitation("Today was made");
        Diagnose diagnose= new Diagnose("Cancer","Very bad diagnose");
       // Medicament medicament= new Medicament("Andubil");

      //  Patient patient= new Patient("Nasko","Petrov","@mail.bg","Face.pic",true);

        entityManager.getTransaction().begin();

       // entityManager.persist(diagnose);
        //entityManager.persist(medicament);
       // entityManager.persist(visitation);
     //   entityManager.persist(patient);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
