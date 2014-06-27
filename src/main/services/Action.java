package main.services;

import main.exceptions.DbConnectorException;
import main.model.Account;
import main.model.Client;
import main.model.Deposit;

import java.util.HashSet;

public interface Action {

    void updateClientDetails(Client client) throws DbConnectorException;

    Client viewClientDetails(Client client) throws DbConnectorException;
    Client viewClientDetails(long clientId) throws DbConnectorException;

    Account viewAccountDetails(Account account) throws Exception;
    Account viewAccountDetails(long AccountId) throws Exception;

    HashSet<Deposit> viewClientDeposits(Client client) throws DbConnectorException;
}
