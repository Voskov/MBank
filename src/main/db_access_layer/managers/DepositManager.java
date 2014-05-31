package main.db_access_layer.managers;

import main.model.Deposit;

public interface DepositManager {
    long createNewDeposit(Deposit deposit);
    Deposit findDeposit(Deposit deposit);
    Deposit findDeposit(long depositId);
    void updateDeposit(Deposit deposit);
    void drawDeposit(long deposit_id);
}
