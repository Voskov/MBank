package main.managers;

import main.model.Account;

public interface AccountManager {
    void createAccount(long id, long client_id, double balance, double credit_limit, String comment);
    void deleteAccount(long account_id);
    void withdrawFromAccount(long account_id, double amount);

    void createAccount(Account account);
    void deleteAccount(Account account);
    void withdrawFromAccount(Account account, double withdraw_amount);
    void depositToAccount(long account_id, double deposit_amount);

}
