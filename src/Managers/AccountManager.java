package Managers;

public interface AccountManager {
    void createAccount(long id, long client_id, double balance, double credit_limit, String comment);
    void deleteAccount(long account_id);
    void withdrawFromAccount(long account_id, double amount);
    void depositToAccount(long account_id, double amount);
}
