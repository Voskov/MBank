package init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitiateDB {
    public static void main(String[] args) {
        restartDb();
    }

    public static void restartDb(){
        DropDb.dropAllTables();
        createDb();
    }

    public static void createDb() {

        String db_address = "jdbc:derby://localhost:1527/MBank;create=true";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db_address);
            try {
//                System.out.println("Connection established successfully");

                Statement statement = connection.createStatement();

                createClientsTable(statement);
                createAccountsTable(statement);
                createDepositsTable(statement);
                createActivitiesTable(statement);
                createPropertiesTable(statement);
                fillProperyTable(statement);


                System.out.println("DB created");
            } catch (SQLException e) {
                connection.close();
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createPropertiesTable(Statement statement) throws SQLException {
        statement.executeUpdate("create table Properties(prop_key VARCHAR(40), prop_value VARCHAR(40), PRIMARY KEY (prop_key))");
//        System.out.println("Properties was created");
    }

    private static void createActivitiesTable(Statement statement) throws SQLException {
        statement.executeUpdate("create table Activity(activity_id  BIGINT NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),client_id  BIGINT NOT NULL, amount DOUBLE, activity_date DATE, commision DOUBLE, description VARCHAR(50))");
//        System.out.println("Activities was created");
    }

    private static void createDepositsTable(Statement statement) throws SQLException {
        statement.executeUpdate("create table Deposits(deposit_id  BIGINT NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),client_id  BIGINT NOT NULL, balance DOUBLE, type VARCHAR(6), estimated_balance BIGINT, opening_date DATE, closing_date DATE)");
//        System.out.println("Accounts was created");
    }

    private static void createAccountsTable(Statement statement) throws SQLException {
        statement.executeUpdate("create table Accounts(account_id BIGINT NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),client_id  BIGINT NOT NULL, balance DOUBLE, credit_limit DOUBLE, comment VARCHAR(50))");
//        System.out.println("Accounts was created");
    }

    private static void createClientsTable(Statement statement) throws SQLException {
        statement.executeUpdate("create table Clients(client_id BIGINT NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), client_name VARCHAR(20), password VARCHAR(20), type VARCHAR(10), address VARCHAR(50), email VARCHAR(20), phone VARCHAR(15), comment VARCHAR(50))");
//        System.out.println("Clients was created");
    }

    private static void fillProperyTable(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO Properties VALUES ('regular_deposit_rate', '10000')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('gold_deposit_rate', '100000')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('platinum_deposit_rate', '1000000')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('regular_deposit_commission', '0.015')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('gold_deposit_commission', '0.01')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('platinum_deposit_commission', '0.005')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('regular_deposit_credit', '100000')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('gold_deposit_credit', '1000000')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('platinum_deposit_credit', '99999999999999999999')"); //TODO - Do something better
        statement.executeUpdate("INSERT INTO Properties VALUES ('commission_rate', '0.5')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('regular_daily_interest', '5.0/365')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('gold_daily_interest', '7.0/365')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('platinum_daily_interest', '8.0/365')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('pre_open_fee', '0.01')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('admin_username', 'system')");
        statement.executeUpdate("INSERT INTO Properties VALUES ('admin_password', 'admin')");
//        System.out.println("properties was filled");
    }
}