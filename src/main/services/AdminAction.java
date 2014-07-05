package main.services;

import main.AccountType;
import main.exceptions.DbConnectorException;
import main.model.Account;
import main.model.Activity;
import main.model.Client;
import main.model.Deposit;

import java.sql.SQLException;

public interface AdminAction extends Action{

    void addNewClient(Client client, double initialAmount) ;

    void removeClient(long client_id) throws DbConnectorException;
    void removeClient(Client client) throws DbConnectorException;

    void createNewAccount(Account account) throws DbConnectorException;

    double removeAccount(Account account) throws DbConnectorException;

    Client viewAllClientsDetails(long client_id) throws DbConnectorException;

    Account viewAllAccountsDetails(long account_id) ;

    Deposit viewAllDepositsDetails(long depositId) throws DbConnectorException;

    void ViewAllActivitiesDetails();

    Activity ViewAllActivitiesDetails(long activityId) throws SQLException, DbConnectorException;

    void updateSystemProperty(String property, String value) throws DbConnectorException;
    void updateSystemProperty(String property, double value) throws DbConnectorException;
    void updateSystemProperty(AccountType type, String property, String value) throws DbConnectorException;
    void updateSystemProperty(AccountType type, String property, double value) throws DbConnectorException;

    void updateClientDetails(Client updatedClient) throws DbConnectorException;

    double viewSystemProperty(String property) ;
    double viewSystemProperty(AccountType type, String property) ;
}
