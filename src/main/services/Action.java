package main.services;

import main.model.Account;
import main.model.Client;
import main.model.Deposit;

import java.util.HashSet;

public interface Action {

    void updateClientDetails(Client client);

    Client viewClientDetails(Client client);
    Client viewClientDetails(long clientId);

    Account viewAccountDetails(Account account) throws Exception;
    Account viewAccountDetails(long AccountId) throws Exception;

    HashSet<Deposit> viewClientDeposits();
}
