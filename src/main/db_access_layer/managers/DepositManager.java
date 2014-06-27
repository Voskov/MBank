package main.db_access_layer.managers;

import main.exceptions.DbConnectorException;
import main.model.Deposit;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;

public interface DepositManager {
    void createNewDeposit(Deposit deposit) throws DbConnectorException;
    Deposit findDeposit(Deposit deposit) throws DbConnectorException;
    Deposit findDeposit(long depositId) throws DbConnectorException;
    void updateDeposit(Deposit deposit) throws DbConnectorException;
    void drawDeposit(long depositId) throws DbConnectorException;

    HashSet<Deposit> allClientsDeposits(long clientId) throws DbConnectorException;

    HashSet<Deposit> allDeposits() throws DbConnectorException;

    HashSet<Deposit> allExpiredDeposits() throws DbConnectorException;
    Deposit buildDeposit(ResultSet res) throws DbConnectorException;

    void closeDeposit(long depositId) throws DbConnectorException;
    void closeDeposit(Deposit deposit) throws DbConnectorException;
}
