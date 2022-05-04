import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties userInfo = new Properties();
        userInfo.setProperty("user", "****");
        userInfo.setProperty("password", "*****");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", userInfo);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT u.user_name, u.first_name, u.last_name, count(ug.user_id) as num FROM users AS u\n" +
                        "JOIN users_games AS ug ON u.id=ug.user_id\n" +
                        "WHERE u.user_name=?\n" +
                        "GROUP BY u.id");


        String username = scanner.nextLine();
        statement.setString(1, username);

        ResultSet rs = statement.executeQuery();


        if (rs.next()) {
            System.out.printf("User: %s", username).println();
            System.out.printf("%s %s has played %d games",
                    rs.getString("u.first_name"), rs.getString("u.last_name"), rs.getInt("num")).println();
        } else {
            System.out.println("No such user exists");
        }
        connection.close();
    }


}
