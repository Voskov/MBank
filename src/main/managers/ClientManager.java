package main.managers;

import main.model.Account;
import main.model.Client;

public interface ClientManager {
    void createClient(long client_id, String client_name, String password, String type, String address, String email, String phone, String comment);

    long createClient(Client client, Account account);

    void updateClient(long client_id, String param, String value);
    void deleteClient(long client_id);
    void updateClientAddress(long client_id, String address);
    void updateClientEmail(long client_id, String email);
    void updateClientPhone(long client_id, String phone);

    void updateClient(Client client, String param, String value);
    void deleteClient(Client client);
    void updateClientAddress(Client client, String address);
    void updateClientEmail(Client client, String email);
    void updateClientPhone(Client client, String phone);

    Client findById(long id);

    void deleteAllClients();
}