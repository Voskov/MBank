package main.services;

import main.model.Account;
import main.model.Activity;
import main.model.Client;
import main.model.Deposit;

import java.sql.SQLException;

public interface AdminAction extends Action{

    void addNewClient(Client client, double initialAmount) throws Exception;

    void removeClient(long client_id) throws Exception;
    void removeClient(Client client) throws Exception;

    void createNewAccount(Account account);

    void removeAccount(Account account);

    Client viewAllClientsDetails(long client_id);

    Account viewAllAccountsDetails(long account_id);

    Deposit viewAllDepositsDetails(long depositId);

    void ViewAllActivitiesDetails();

    Activity ViewAllActivitiesDetails(long activityId) throws SQLException;

    void updateSystemProperty();

}
