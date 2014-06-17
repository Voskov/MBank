package main.db_access_layer.managers.impl;

import main.db_access_layer.managers.AccountManager;
import main.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.*;
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
//            sqlStatement = "INSERT INTO Accounts VALUES(" + account.getAccountId() + ", " + account.getClientId() + ", " + account.getBalance() + ", " + account.getCreditLimit() + ", '" + account.getComment() + "')";
            sqlStrBldr = new StringBuilder("INSERT INTO Accounts ");
            sqlStrBldr.append(" (client_id, balance, credit_limit, comment) ");
            sqlStrBldr.append("VALUES(");
            sqlStrBldr.append(account.getClientId()).append(", ");
            sqlStrBldr.append(account.getBalance()).append(", ");
            sqlStrBldr.append(account.getCreditLimit()).append(", '");
            sqlStrBldr.append(account.getComment()).append("')");
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Account " + account.getAccountId() + " was created on DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Account" + account.getAccountId() + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(Account account) {
        sqlStr = "DELETE FROM Accounts WHERE account_id=" + account.getAccountId();
        String logMessage = "Account " + account.getAccountId() + " was deleted from DB";
        executeStatement(sqlStr, logMessage);
    }

    public void createAccount(long accountId, long clientId, double balance, double creditLimit, String comment) {
        try {
            sqlStrBldr = new StringBuilder("INSERT INTO Accounts VALUES(");
            sqlStrBldr.append(accountId).append(", ");
            sqlStrBldr.append(clientId).append(", ");
            sqlStrBldr.append(balance).append(", ");
            sqlStrBldr.append(creditLimit).append(", '");
            sqlStrBldr.append(comment).append("')");
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Account " + accountId + " was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Account" + accountId + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(long accountId) {
        sqlStr = "DELETE FROM Accounts WHERE account_id=" + accountId;
        String logMsg = "Account " + accountId + " was deleted from DB";
        executeStatement(sqlStr, logMsg);
    }

    @Override
    public void depositToAccount(long accountId, double depositdepositAmountamount) {
        sqlStatement = "SELECT balance FROM Accounts";
        try {
            double balance, new_balance = 0;
            ResultSet res = stmt.executeQuery(sqlStatement);
            if (res.next()) {
                balance = res.getDouble(1);
                new_balance = balance + depositdepositAmountamount;
            }
            sqlStatement = "UPDATE Accounts SET balance=" + new_balance;
            System.out.println(sqlStatement);
            stmt.executeUpdate(sqlStatement);
            String msg = depositdepositAmountamount + " was deposited to account " + accountId + ". The new balance is " + new_balance;
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
        sqlStatement = "SELECT balance FROM Accounts WHERE account_id=" + account.getAccountId();
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

    @Override
    public void deleteAllAccounts() {
        sqlStr = "DELETE FROM Accounts";
        String log_message = "All accounts were deleted";
        executeStatement(sqlStr, log_message);
    }

    @Override
    public Account findAccount(long accountId) throws Exception {
        if (accountId == 0) throw new Exception("accountId cannot be 0");
        sqlStrBldr = new StringBuilder("SELECT * FROM accounts WHERE account_id=").append(accountId);
        Account account = null;
        try {
            ResultSet resultSet = stmt.executeQuery(sqlStrBldr.toString());
            if (resultSet.next()) {
                account = new Account(resultSet.getLong(1), resultSet.getLong(2), resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public Account findAccount(Account account) throws Exception {
        return findAccount(account.getAccountId());
    }

    @Override
    public void updateAccount(Account account) {
        sqlStrBldr = new StringBuilder("UPDATE Accounts SET ") ;
        sqlStrBldr.append("balance=").append(account.getBalance());
        sqlStrBldr.append(", credit_limit=").append(account.getCreditLimit());
        sqlStrBldr.append(", comment='").append(account.getComment()).append("'");
        try {
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Account " + account.getAccountId() + " was updated.";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            String msg = "Account " + account.getAccountId() + " could not be updated.";
            LOGGER.log(Level.WARNING, msg);
            e.printStackTrace();
        }
    }

    @Override
    public long countAllAccounts(){
        sqlStrBldr = new StringBuilder("SELECT count(*) FROM Accounts");
        try {
            ResultSet res = stmt.executeQuery(sqlStrBldr.toString());
            if (res.next()){
                return res.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public HashSet<Account> allClientsAccounts(long clientId) {
        sqlStrBldr = new StringBuilder("SELECT * FROM Accounts");
        HashSet<Account> allAccounts = new HashSet<Account>();
        try {
            ResultSet res = stmt.executeQuery(sqlStrBldr.toString());
            while (res.next()){
                allAccounts.add(createAccountFromResult(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAccounts;
    }



    @Override
    public Account createAccountFromResult(ResultSet res){
        Account account = null;
        try {
            account = new Account(res.getLong(1), res.getLong(2), res.getDouble(3), res.getDouble(4), res.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    //    @Override
//    public void withdrawFromAccount(Account account, double withdraw_amount){
//        withdrawFromAccount(account.getAccountId(), withdraw_amount);
//    }
//
//    @Override
//    public void withdrawFromAccount(long account_id, double withdraw_amount) {
//        sqlStatement = "SELECT balance, credit_limit FROM Accounts";
//        try {
//            ResultSet res = stmt.executeQuery(sqlStatement);
//            double balance, cred_lim = 0, new_balance = 0;
//            if (res.next()) {
//                balance = res.getDouble(1);
//                cred_lim = res.getDouble(2);
//                new_balance = balance - withdraw_amount;
//            }
//            if (new_balance >= cred_lim) {
//                sqlStatement = "UPDATE Accounts SET balance=" + new_balance;
//                stmt.executeUpdate(sqlStatement);
//                String msg = withdraw_amount + " was withdrawn from account " + account_id + ". The new balance is " + new_balance;
//                LOGGER.log(Level.INFO, msg);
//            } else {
//                String msg = "Your credit limit isn't large enough to withdraw " + withdraw_amount;
//                LOGGER.log(Level.INFO, msg);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
