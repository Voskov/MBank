package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;
import java.sql.Statement;

public class InitiateDB {
    public static void main(String[] args) {

        String db_address = "jdbc:derby://localhost:1527/MBank;create=true";

        try {
            Connection connection = DriverManager.getConnection(db_address);
            try {


                System.out.println("Connection established successfully");

                Statement statement = connection.createStatement();
                statement.executeUpdate("create table Clients(client_id BIGINT, client_name VARCHAR(20), password VARCHAR(20), type VARCHAR(9), address VARCHAR(50), email VARCHAR(20), phone VARCHAR(15), comment VARCHAR(50), PRIMARY KEY (client_id))");
                System.out.println("Clients was created");

                statement.executeUpdate("create table Accounts(account_id BIGINT, client_id BIGINT, balance DOUBLE, credit_limit DOUBLE, comment VARCHAR(50), PRIMARY KEY (account_id))");
                System.out.println("Accounts was created");

                statement.executeUpdate("create table Deposits(deposit_id BIGINT, client_id BIGINT, balance DOUBLE, type VARCHAR(6), estimated_balance BIGINT, opening_date DATE, closing_date DATE, PRIMARY KEY (deposit_id))");
                System.out.println("Accounts was created");

                statement.executeUpdate("create table Activity(id BIGINT, client_id BIGINT, amount DOUBLE, activity_date DATE, commision DOUBLE, description VARCHAR(50), PRIMARY KEY (id))");
                System.out.println("Activities was created");

                statement.executeUpdate("create table Properties(prop_key VARCHAR(20), prop_value VARCHAR(20), PRIMARY KEY (prop_key))");
                System.out.println("Properties was created");

                connection.close();
            } catch (SQLException e) {
                connection.close();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}