package testing;

import classes.Account;
import classes.AccountType;
import classes.Activity;
import classes.Client;
import db_connector.AccountDbConnector;
import db_connector.AcitivityDbConnector;
import db_connector.ClientDbConnector;

import java.util.Date;

public class TestDbConnector {
    public static void main(String[] args) {
//        activityAdd();
//        clientAdd();
        accountAdd();
    }

    public static void clientAdd() {
        Client cl = new Client(123456, "Voskov", "qwerty", AccountType.GOLD, "Daniel Frish 6", "Ariel@Voskov.com", "054-1234567", "Awesome dude");
        ClientDbConnector clientConnector = new ClientDbConnector();

//        clientConnector.addClient(cl);
        clientConnector.createClient(98765432, "Ariel", "asdfgh", AccountType.GOLD.toString(), "Daniel Frish 6", "Voskov@Ariel.com", "054-987654", "Cool dude");
        clientConnector.disconnect();
    }

    public static void accountAdd() {
        Account acc1 = new Account(123, 12345, 100.0, 1000, "Test Account");
        AccountDbConnector acConn = new AccountDbConnector();
        acConn.addAccount(acc1);
        acConn.addAccount(123456,765432,12345.00,7654,"Another test");
        acConn.disconnect();
    }

    public static void activityAdd() {
        Activity act = new Activity(1234567, 12345, 100.0, new Date(), 5.0, "Test Activity");
        AcitivityDbConnector actCon = new AcitivityDbConnector();
        actCon.addActivity(act);
        actCon.disconnect();
    }
}
