package main.services;

import main.DepositType;
import main.model.Account;
import main.model.Activity;
import main.model.Client;
import main.model.Deposit;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;

public interface ClientAction extends Action{

    void withdrawFromAccount(Account account, double withdrawalAmount) throws Exception;
    void withdrawFromAccount(long accountId, double withdrawalAmount) throws Exception;
    void withdrawFromAccount(Client client, double withdrawalAmount) throws Exception; // Assuming that there's only one account for a client, for now

    void depositToAccount(Client client, double depositAmount) throws Exception;
    void depositToAccount(Account account, double depositAmount) throws Exception;
    void depositToAccount(long accountId, double depositAmount) throws Exception;

    void createNewDeposit(Deposit deposit) throws Exception;
    void createNewDeposit(Deposit deposit, Client client) throws Exception;

    void preOpenDeposit(long deposit_id) throws Exception;

    void updateClientDetails(Client client);

    HashSet<Deposit> viewClientDeposits(long clientId);

    HashSet<Activity> viewClientActivities(Client client) throws SQLException;
}
