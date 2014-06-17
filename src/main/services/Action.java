package main.services;

import main.model.Account;
import main.model.Client;

public interface Action {

    void updateClientDetails(Client client);

    Client viewClientDetails(Client client);
    Client viewClientDetails(long clientId);

    Account viewAccountDetails(Account account);
    Account viewAccountDetails(long AccountId);

    void viewClientDeposits();

    void viewClientActivities();
}
