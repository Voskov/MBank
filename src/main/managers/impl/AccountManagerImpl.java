package main.managers.impl;

import main.managers.AccountManager;
import main.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.logging.Level;

public class AccountManagerImpl extends DbConnectorManagerImpl implements AccountManager {
    private String sqlStatement;
    public AccountManagerImpl() {
        connectToDb();
    }

    public AccountManagerImpl(Statement stmt) {
        DbConnectorManagerImpl.stmt = stmt;
    }

    public void createAccount(Account account) {
        try {
            sqlStatement = "INSERT INTO Accounts VALUES(" + account.getAccount_id() + ", " + account.getClient_id() + ", " + account.getBalance() + ", " + account.getCredit_limit() + ", '" + account.getComment() + "')";
            stmt.executeUpdate(sqlStatement);
            String msg = "Account " + account.getAccount_id() + " was created on DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Account" + account.getAccount_id() + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(Account account) {
        sqlStr = "DELETE FROM Accounts WHERE account_id=" + account.getAccount_id();
        String logMessage = "Account " + account.getAccount_id() + " was deleted from DB";
        executeStatement(sqlStr, logMessage);
    }

    @Override
    public void withdrawFromAccount(Account account, double withdraw_amount) {
        sqlStatement = "Select balance, credit_limit FROM Accounts WHERE account_id=" + account.getAccount_id();
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
                String msg = withdraw_amount + " was withdrawn from account " + account.getAccount_id() + ". The new balance is " + new_balance;
                LOGGER.log(Level.INFO, msg);
            } else {
                String msg = "Your credit limit isn't large enough to withdraw " + withdraw_amount;
                LOGGER.log(Level.INFO, msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createAccount(long id, long client_id, double balance, double credit_limit, String comment) {
        try {
            sqlStatement = "INSERT INTO Accounts VALUES(" + id + ", " + client_id + ", " + balance + ", " + credit_limit + ", '" + comment + "')";
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
        sqlStr = "DELETE FROM Accounts WHERE account_id=" + id;
        String logMsg = "Account " + id + " was deleted from DB";
        executeStatement(sqlStr, logMsg);
    }

    public void withdrawFromAccount(long account_id, double withdraw_amount) {
        sqlStatement = "Select balance, credit_limit FROM Accounts";
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

    @Override
    public void depositToAccount(long account_id, double deposit_amount) {
        sqlStatement = "SELECT balance FROM Accounts";
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
        sqlStatement = "SELECT balance FROM Accounts WHERE account_id" + account_id;
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

    public void checkBalance(Account account) {
        sqlStatement = "SELECT balance FROM Accounts WHERE account_id=" + account.getAccount_id();
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

    public void deleteAllAccounts(){
        sqlStr = "DELETE FROM Accounts";
        String log_message = "All accounts were deleted";
        executeStatement(sqlStr, log_message);
    }

}
