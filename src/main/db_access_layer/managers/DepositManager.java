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
    void drawDeposit(long deposit_id);

    ArrayList<Deposit> allClientsDeposits(long client_id);

    HashSet<Deposit> allDeposits();

    HashSet<Deposit> allExpiredDeposits();
    Deposit buildDeposit(ResultSet res);

    void closeDeposit(long deposit_id);
    void closeDeposit(Deposit deposit);
}
