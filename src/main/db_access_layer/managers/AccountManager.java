package main.db_access_layer.managers;

import main.model.Account;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface AccountManager {
    void createAccount(long id, long client_id, double balance, double credit_limit, String comment);
    void deleteAccount(long account_id);

    void createAccount(Account account);
    void deleteAccount(Account account);
    void depositToAccount(long account_id, double deposit_amount);

    void deleteAllAccounts();


    Account findAccount(long account_id);
    Account findAccount(Account account);

    void updateAccount(Account account);

    long countAllAccounts();

    HashSet<Account> allClientsAccounts(long client_id);

    Account createAccountFromResult(ResultSet res);


//These shouldn't be here...
//    void withdrawFromAccount(long account_id, double amount);
//    void withdrawFromAccount(Account account, double withdraw_amount);

}
