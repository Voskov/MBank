package main.db_access_layer.managers;

import main.model.Account;
import main.model.Client;

public interface ClientManager {
    void createClient(long client_id, String client_name, String password, String type, String address, String email, String phone, String comment);

    long createClient(Client client, Account account);
    void createClient(Client client);

    void updateClient(long client_id, String param, String value);
    void updateClient(Client client);

    void updateClientAddress(long client_id, String address);

    void updateClientEmail(long client_id, String email);
    void updateClientPhone(long client_id, String phone);
    void updateClientAddress(Client client, String address);
    void updateClientEmail(Client client, String email);
    void updateClient(Client client, String param, String value);
    void updateClientPhone(Client client, String phone);

    void deleteClient(Client client);
    void deleteClient(long client_id);

    Client findClient(long id);
    Client findClient(String username);
    Client findClient(Client client);

    void deleteAllClients();
}