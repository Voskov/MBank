package db_connector;

import Managers.AccountManager;
import classes.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;

public class AccountDbConnector extends DbConnector implements AccountManager {
    public AccountDbConnector() {
        connectToDb();
    }

    public void createAccount(Account acc) {
        try {

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

    public void createAccount(long id, long client_id, double balance, double credit_limit, String comment) {
        try {

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

    public void deleteAccount(long id) {
        try {
            String sqlStatement = "DELETE FROM Accounts WHERE account_id=" + id;
            stmt.executeUpdate(sqlStatement);
            String msg = "Account " + id + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void withdrawFromAccount(long account_id, double withdraw_amount) {
        String sqlStatement = "Select balance, credit_limit FROM Accounts";
        try {
            ResultSet res = stmt.executeQuery(sqlStatement);
            double balance, cred_lim = 0, new_balance = 0;
            if (res.next()) {
                balance = res.getDouble(1);
                cred_lim = res.getDouble(2);
                new_balance = balance - withdraw_amount;
            }
            if (new_balance >= cred_lim) {
                sqlStatement = "UPDATE Accounts SET balance=" + new_balance;
                stmt.executeUpdate(sqlStatement);
                String msg = withdraw_amount + " was withdrawn from account " + account_id + ". The new balance is " + new_balance;
                LOGGER.log(Level.INFO, msg);
            } else {
                String msg = "Your credit limit isn't large enough to withdraw " + withdraw_amount;
                LOGGER.log(Level.INFO, msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void depositToAccount(long account_id, double deposit_amount) {
        String sqlStatement = "SELECT balance FROM Accounts";
        try {
            double balance, new_balance = 0;
            ResultSet res = stmt.executeQuery(sqlStatement);
            if (res.next()) {
                balance = res.getDouble(1);
                new_balance = balance + deposit_amount;
            }
            sqlStatement = "UPDATE Accounts SET balance=" + new_balance;
            System.out.println(sqlStatement);
            stmt.executeUpdate(sqlStatement);
            String msg = deposit_amount + " was deposited to account " + account_id + ". The new balance is " + new_balance;
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkBalance(long account_id) {
        String sqlStatement = "SELECT balance FROM Accounts";
        try {
            ResultSet res = stmt.executeQuery(sqlStatement);
            if (res.next()) {
                double balance = res.getDouble(1);
                LOGGER.log(Level.INFO, String.valueOf(balance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
