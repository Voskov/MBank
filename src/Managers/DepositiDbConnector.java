package Managers;


import classes.model.Deposit;
import main.DepositType;

public interface DepositiDbConnector {
    void createNewDeposit(long deposit_id, long client_id, double balance, DepositType type, long estimated_balance, String opening_date, String closing_date);
    void createNewDeposit(Deposit deposit);
    void closeDeposit(long deposit_id);
    void drawDeposit(long deposit_id);
}
