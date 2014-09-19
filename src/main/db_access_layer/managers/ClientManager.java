package main.db_access_layer.managers;

import main.exceptions.DbConnectorException;
import main.model.Account;
import main.model.Client;

import java.sql.SQLException;

public interface ClientManager {
    void createClient(long clientId, String clientName, String password, String type, String address, String email, String phone, String comment) throws DbConnectorException;

    long createClient(Client client, Account account) throws DbConnectorException;
    void createClient(Client client) throws DbConnectorException;

    void updateClient(long clientId, String param, String value) throws DbConnectorException;
    void updateClient(Client client) throws DbConnectorException;

    void updateClientAddress(long clientId, String address) throws DbConnectorException;

    void updateClientEmail(long clientId, String email) throws DbConnectorException;
    void updateClientPhone(long clientId, String phone) throws DbConnectorException;
    void updateClientAddress(Client client, String address) throws DbConnectorException;
    void updateClientEmail(Client client, String email) throws DbConnectorException;
    void updateClient(Client client, String param, String value) throws DbConnectorException;
    void updateClientPhone(Client client, String phone) throws DbConnectorException;

    void deleteClient(Client client) throws DbConnectorException;
    void deleteClient(long clientId) throws DbConnectorException;

    Client findClient(long id) throws DbConnectorException;
    Client findClient(String username) throws DbConnectorException;
    Client findClient(Client client) throws DbConnectorException;

    void deleteAllClients() throws DbConnectorException;

    java.sql.ResultSet getAllClients() throws DbConnectorException, SQLException;
}