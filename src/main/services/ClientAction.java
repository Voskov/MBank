package main.services;

import main.DepositType;
import main.exceptions.ClientActionException;
import main.exceptions.ClientException;
import main.exceptions.DbConnectorException;
import main.model.Account;
import main.model.Activity;
import main.model.Client;
import main.model.Deposit;

import javax.security.auth.login.AccountException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;

public interface ClientAction extends Action{

    void withdrawFromAccount(Account account, double withdrawalAmount) throws DbConnectorException, ClientException;
    void withdrawFromAccount(long accountId, double withdrawalAmount) throws DbConnectorException, ClientException;
    void withdrawFromAccount(Client client, double withdrawalAmount) throws DbConnectorException, ClientException; // Assuming that there's only one account for a client, for now

    void depositToAccount(Client client, double depositAmount) throws DbConnectorException, AccountException, ClientException;
    void depositToAccount(Account account, double depositAmount) throws DbConnectorException, AccountException;
    void depositToAccount(long accountId, double depositAmount) throws AccountException, DbConnectorException;

    void createNewDeposit(Client client, Date openingDate, double cmount) throws DbConnectorException, ClientActionException;

    void createNewDeposit(Deposit deposit) throws DbConnectorException, ClientActionException;
    void createNewDeposit(Deposit deposit, Client client) throws DbConnectorException, ClientActionException;

    void preOpenDeposit(long deposit_id) throws DbConnectorException, ClientException;

    void updateClientDetails(Client client) throws DbConnectorException;

    HashSet<Deposit> viewClientDeposits(long clientId) throws DbConnectorException;

    HashSet<Activity> viewClientActivities(Client client) throws SQLException, DbConnectorException;
}
