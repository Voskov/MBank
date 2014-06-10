package main.services;

import main.model.Account;
import main.model.Client;

public interface AdminAction extends Action{

    void addNewClient(Client client, double initialAmount) throws Exception;

    void removeClient(long client_id) throws Exception;
    void removeClient(Client client) throws Exception;

    void createNewAccount(Account account);

    void removeAccount(Account account);

    void viewAllClientsDetails();

    void viewAllAccountsDetails();

    void viewAllDepositsDetails();

    void ViewAllActivitiesDetails();

    void updateSystemProperty();

}
