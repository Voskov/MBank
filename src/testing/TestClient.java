package testing;

import classes.AccountType;
import classes.Client;
import db_connector.ClientDbConnector;

public class TestClient {

    public static void main(String[] args) {
        clientAdd();
//        clientDelete();
//        clientUpdatePhone();
    }
    public static void clientAdd() {
//        Client cl = new Client(123456, "Voskov", "qwerty", AccountType.GOLD, "Daniel Frish 6", "Ariel@Voskov.com", "054-1234567", "Awesome dude");
        ClientDbConnector clientConnector = new ClientDbConnector();

//        clientConnector.addClient(cl);
        clientConnector.createClient(98765432, "Ariel", "asdfgh", AccountType.GOLD.toString(), "Daniel Frish 6", "Voskov@Ariel.com", "054-987654", "Cool dude");
        clientConnector.disconnect();
    }

    public static void clientDelete(){
        ClientDbConnector CC = new ClientDbConnector();
        CC.deleteClient(98765432);
        CC.disconnect();
    }

    public static void clientUpdatePhone(){
        ClientDbConnector CC = new ClientDbConnector();
        CC.updateClientPhone(98765432, "123456");
    }

}