package main.services;

import main.AccountType;
import main.exceptions.DbConnectorException;
import main.model.Account;
import main.model.Activity;
import main.model.Client;
import main.model.Deposit;

import java.sql.SQLException;

public interface AdminAction extends Action{

    void addNewClient(Client client, double initialAmount) throws Exception;

    void removeClient(long client_id) throws Exception;
    void removeClient(Client client) throws Exception;

    void createNewAccount(Account account) throws DbConnectorException;

    double removeAccount(Account account) throws Exception;

    Client viewAllClientsDetails(long client_id) throws DbConnectorException;

    Account viewAllAccountsDetails(long account_id) throws Exception;

    Deposit viewAllDepositsDetails(long depositId) throws DbConnectorException;

    void ViewAllActivitiesDetails();

    Activity ViewAllActivitiesDetails(long activityId) throws SQLException, DbConnectorException;

    void updateSystemProperty(String property, String value) throws DbConnectorException;
    void updateSystemProperty(String property, double value) throws DbConnectorException;
    void updateSystemProperty(AccountType type, String property, String value) throws DbConnectorException;
    void updateSystemProperty(AccountType type, String property, double value) throws DbConnectorException;

    void updateClientDetails(Client updatedClient) throws DbConnectorException;

    double viewSystemProperty(String property) throws Exception;
    double viewSystemProperty(AccountType type, String property) throws Exception;
}
