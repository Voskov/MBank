package Managers;

public interface ClientManager {
    void createClient(long client_id, String client_name, String password, String type, String address, String email, String phone, String comment);
    void updateClient(long client_id, String param, String value);
    void deleteClient(long client_id);
    void updateClientAddress(long client_id, String address);
    void updateClientEmail(long client_id, String email);
    void updateClientPhone(long client_id, String phone);


}
