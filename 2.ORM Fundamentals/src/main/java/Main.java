import annotations.Entity;
import entities.Address;
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        MyConnector.createConnection("****", "*****", "custom-orm");
        Connection connection = MyConnector.getConnection();
        EntityManager<User> userEntityManager = new EntityManager<>(connection);
        EntityManager<Address> addressEntityManager = new EntityManager<>(connection);

        addressEntityManager.doCreate(Address.class);
       /* User user = new User("Stancho", 25, LocalDate.now());
        user.setUsername("Pesho_NEW");
        user.setLastLogIn(user.getRegistration());
        // userEntityManager.doCreate(User.class);
        // userEntityManager.doAlter(User.class);
        userEntityManager.persist(user);


        Iterable<User> first = userEntityManager.find(User.class);
        System.out.println(first.toString());


        User userToDelete= userEntityManager.findFirst(User.class, "id=3");
        System.out.println(userToDelete);

        userEntityManager.delete(userToDelete);

        Iterable<User> second = userEntityManager.find(User.class);
        System.out.println(second.toString());
*/
        connection.close();
    }
}
