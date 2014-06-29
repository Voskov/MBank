package main.db_access_layer.managers;

import main.exceptions.DbConnectorException;
import main.model.Account;

import java.sql.ResultSet;
import java.util.HashSet;

public interface AccountManager {
    void createAccount(long id, long clientId, double balance, double creditLimit, String comment) throws DbConnectorException;
    void deleteAccount(long accountId) throws DbConnectorException;

    void createAccount(Account account) throws DbConnectorException;
    void deleteAccount(Account account) throws DbConnectorException;

    void deleteAllAccounts() throws DbConnectorException;


    void depositToAccount(long accountId, double depositdepositAmountamount) throws DbConnectorException;

    Account findAccount(long accountId) throws DbConnectorException;
    Account findAccount(Account account) throws DbConnectorException;

    void updateAccount(Account account) throws DbConnectorException;

    long countAllAccounts() throws DbConnectorException;

    HashSet<Account> allClientsAccounts(long clientId) throws DbConnectorException;

    Account createAccountFromResult(ResultSet res) throws DbConnectorException;
}
