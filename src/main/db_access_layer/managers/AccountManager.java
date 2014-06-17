package main.db_access_layer.managers;

import main.model.Account;
import main.model.Activity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface AccountManager {
    void createAccount(long id, long clientId, double balance, double creditLimit, String comment);
    void deleteAccount(long accountId);

    void createAccount(Account account);
    void deleteAccount(Account account);
    void depositToAccount(long accountId, double depositdepositAmountamount);

    void deleteAllAccounts();


    Account findAccount(long accountId) throws Exception;
    Account findAccount(Account account) throws Exception;

    void updateAccount(Account account);

    long countAllAccounts();

    HashSet<Account> allClientsAccounts(long clientId);

    Account createAccountFromResult(ResultSet res);
}
