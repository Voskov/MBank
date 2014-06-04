package main.services;

import main.model.Account;
import main.model.Client;

/**
 * Created by Einstine on 04/06/2014.
 */
public interface AdminAction extends Action{

    Client addNewClient();

    void removeClient();

    Account createNewAccount();

    void removeAccount();

    void viewAllClientsDetails();

    void viewAllAccountsDetails();

    void viewAllDepositsDetails();

    void ViewAllActivitiesDetails();

    void updateSystemProperty();

}
