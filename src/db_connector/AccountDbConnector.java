package db_connector;

import classes.Account;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.logging.Level;

public class AccountDbConnector extends DbConnector {
    public AccountDbConnector() {
        connectToDb();
    }

    public void addAccount(Account acc) {
        try {
            Statement stmt = con.createStatement();
            String sqlStatement = "INSERT INTO Accounts VALUES(" + acc.getAccount_id() + ", " + acc.getClient_id() + ", " + acc.getBalance() + ", " + acc.getCredit_limit() + ", '" + acc.getComment() + "')";
            System.out.println(sqlStatement);
            stmt.executeUpdate(sqlStatement);
            System.out.println("Account added");
            String msg = "Account " + acc.getAccount_id() + " was created on DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Account" + acc.getAccount_id() + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(long id, long client_id, double balance, double credit_limit, String comment) {
        try {
            Statement stmt = con.createStatement();
            String sqlStatement = "INSERT INTO Accounts VALUES(" + id + ", " + client_id + ", " + balance + ", " + credit_limit + ", '" + comment + "')";
            stmt.executeUpdate(sqlStatement);
            String msg = "Account " + id + " was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Account" + id + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(long id){
        try {
            Statement stmt = con.createStatement();
            String sqlStatement = "DELETE FROM Accounts WHERE account_id=" + id;
            stmt.executeUpdate(sqlStatement);
            String msg = "Account " + id + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
