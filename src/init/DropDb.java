package init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropDb {

    public static void dropAllTables(){
        String dbAddress = "jdbc:derby://localhost:1527/MBank;create=true";

        try {
            Connection connection = DriverManager.getConnection(dbAddress);
            try {
//                System.out.println("Connection established successfully");

                Statement statement = connection.createStatement();
                statement.executeUpdate("DROP TABLE Clients");
//                System.out.println("Clients was dropped");

                statement.executeUpdate("DROP TABLE Accounts");
//                System.out.println("Accounts was dropped");

                statement.executeUpdate("DROP TABLE Deposits");
//                System.out.println("Accounts was dropped");

                statement.executeUpdate("DROP TABLE Activity");
//                System.out.println("Activities was dropped");

                statement.executeUpdate("DROP TABLE Properties");
//                System.out.println("Properties was dropped");

                System.out.println("Tables dropped");
                connection.close();
            } catch (SQLException e) {
                connection.close();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        dropAllTables();
    }
}