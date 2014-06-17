package main.db_access_layer.managers;

import main.model.Deposit;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;

public interface DepositManager {
    void createNewDeposit(Deposit deposit);
    Deposit findDeposit(Deposit deposit);
    Deposit findDeposit(long depositId);
    void updateDeposit(Deposit deposit);
    void drawDeposit(long depositId);

    HashSet<Deposit> allClientsDeposits(long clientId);

    HashSet<Deposit> allDeposits();

    HashSet<Deposit> allExpiredDeposits();
    Deposit buildDeposit(ResultSet res);

    void closeDeposit(long depositId);
    void closeDeposit(Deposit deposit);
}
