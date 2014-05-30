package main.db_access_layer.managers;

import main.model.Deposit;

public interface DepositManager {
    long createNewDeposit(Deposit deposit);
    void drawDeposit(long deposit_id);
}
