package Managers;

import classes.DepositType;

public interface DepositManager {
    void createNewDeposit(long deposit_id, long client_id, double balance, DepositType type, long estimated_balance, String opening_date, String closing_date);
    void closeDeposit();
    double preopenDeposit();

}
