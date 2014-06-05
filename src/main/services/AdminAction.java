package main.services;

import main.model.Account;
import main.model.Client;

/**
 * Created by Einstine on 04/06/2014.
 */
public interface AdminAction extends Action{

    Client addNewClient();

    void removeClient(long client_id);
    void removeClient(Client client);

    void createNewAccount(Account account);

    void removeAccount(Account account);

    void viewAllClientsDetails();

    void viewAllAccountsDetails();

    void viewAllDepositsDetails();

    void ViewAllActivitiesDetails();

    void updateSystemProperty();

}
