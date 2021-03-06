package main.db_access_layer.managers.impl;

import main.db_access_layer.managers.AccountManager;
import main.exceptions.DbConnectorException;
import main.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;

public class AccountManagerImpl extends DbConnectorManagerImpl implements AccountManager {
    private String sqlStatement;

    public AccountManagerImpl() throws DbConnectorException {
    }

    public void createAccount(Account account) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("INSERT INTO Accounts ");
        sqlStrBldr.append(" (client_id, balance, credit_limit, comment) ");
        sqlStrBldr.append("VALUES(");
        sqlStrBldr.append(account.getClientId()).append(", ");
        sqlStrBldr.append(account.getBalance()).append(", ");
        sqlStrBldr.append(account.getCreditLimit()).append(", '");
        sqlStrBldr.append(account.getComment()).append("')");
        executeUpdate(sqlStrBldr);
        String msg = "Account " + account.getAccountId() + " was created on DB";
        LOGGER.log(Level.INFO, msg);
    }

    @Override
    public void deleteAccount(Account account) throws DbConnectorException {
        sqlStr = "DELETE FROM Accounts WHERE account_id=" + account.getAccountId();
        executeUpdate(sqlStr);
        String logMessage = "Account " + account.getAccountId() + " was deleted from DB";
        LOGGER.log(Level.INFO, logMessage);
    }

    public void createAccount(long accountId, long clientId, double balance, double creditLimit, String comment) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("INSERT INTO Accounts VALUES(");
        sqlStrBldr.append(accountId).append(", ");
        sqlStrBldr.append(clientId).append(", ");
        sqlStrBldr.append(balance).append(", ");
        sqlStrBldr.append(creditLimit).append(", '");
        sqlStrBldr.append(comment).append("')");
        executeUpdate(sqlStrBldr.toString());
        String msg = "Account " + accountId + " was created on DB";
        LOGGER.log(Level.INFO, msg);
    }

    public void deleteAccount(long accountId) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("DELETE FROM Accounts WHERE account_id=").append(accountId);
        executeUpdate(sqlStrBldr.toString());
        String logMsg = "Account " + accountId + " was deleted from DB";
        LOGGER.log(Level.INFO, logMsg);
    }

    @Override
    public void deleteAllAccounts() throws DbConnectorException {
        sqlStr = "DELETE FROM Accounts";
        String logMessage = "All accounts were deleted";
        executeUpdate(sqlStr);
        LOGGER.info(logMessage);
    }

    @Override
    public void depositToAccount(long accountId, double depositdepositAmountamount) throws DbConnectorException {
//        sqlStatement = "SELECT balance FROM Accounts";
        sqlStrBldr = new StringBuilder("SELECT balance FROM Accounts WHERE account_id=").append(accountId);
        double balance, new_balance = 0;
        ResultSet res = executeQuery(sqlStrBldr.toString());
        try {
            if (res.next()) {
                balance = res.getDouble(1);
                new_balance = balance + depositdepositAmountamount;
            }
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        sqlStrBldr = new StringBuilder("UPDATE Accounts SET balance=").append(new_balance);
        sqlStrBldr.append(" WHERE account_id=").append(accountId);
        executeUpdate(sqlStrBldr.toString());
        String msg = depositdepositAmountamount + " was deposited to account " + accountId + ". The new balance is " + new_balance + " - AM-715";
        LOGGER.log(Level.INFO, msg);
    }

    @Override
    public Account findAccount(long accountId) throws DbConnectorException {
        if (accountId == 0) throw new DbConnectorException("accountId cannot be 0");
        sqlStrBldr = new StringBuilder("SELECT * FROM accounts WHERE account_id=").append(accountId);
        Account account = null;
        try {
            ResultSet resultSet = executeQuery(sqlStrBldr.toString());
            if (resultSet.next()) {
                account = new Account(resultSet.getLong(1), resultSet.getLong(2), resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getString(5));
            }
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        return account;
    }

    public Account findAccount(Account account) throws DbConnectorException {
        return findAccount(account.getAccountId());
    }

    @Override
    public void updateAccount(Account account) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("UPDATE Accounts SET ") ;
        sqlStrBldr.append("balance=").append(account.getBalance());
        sqlStrBldr.append(", credit_limit=").append(account.getCreditLimit());
        sqlStrBldr.append(", comment='").append(account.getComment()).append("'");
        executeUpdate(sqlStrBldr.toString());
        String msg = "Account " + account.getAccountId() + " was updated.";
        LOGGER.log(Level.INFO, msg);
    }

    @Override
    public long countAllAccounts() throws DbConnectorException {
        sqlStrBldr = new StringBuilder("SELECT count(*) FROM Accounts");
        try {
            ResultSet res = executeQuery(sqlStrBldr.toString());
            if (res.next()){
                return res.getLong(1);
            }
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        return 0;
    }

    @Override
    public HashSet<Account> allClientsAccounts(long clientId) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("SELECT * FROM Accounts");
        HashSet<Account> allAccounts = new HashSet<Account>();
        try {
            ResultSet res = executeQuery(sqlStrBldr.toString());
            while (res.next()){
                allAccounts.add(createAccountFromResult(res));
            }
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        return allAccounts;
    }



    @Override
    public Account createAccountFromResult(ResultSet res) throws DbConnectorException {
        Account account = null;
        try {
            account = new Account(res.getLong(1), res.getLong(2), res.getDouble(3), res.getDouble(4), res.getString(5));
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        return account;
    }

    @Override
    public HashSet<Long> getAllAccountIds() throws DbConnectorException {
        sqlStrBldr = new StringBuilder("SELECT account_id FROM accounts");
        HashSet<Long> allAccountIds = new HashSet<Long>();
        try {
            ResultSet res = executeQuery(sqlStrBldr);
            while (res.next()) {
                allAccountIds.add(res.getLong(1));
            }
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        return allAccountIds;
    }

    private Account buildAccount(ResultSet resultSet) {
        Account account = null;
        try {
            account = new Account(resultSet.getLong(1), resultSet.getLong(2), resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }


}

