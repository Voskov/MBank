package test;

import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.db_access_layer.managers.impl.ActivityManagerImpl;
import main.model.Account;
import main.model.Activity;

import java.util.Date;

public class TestDbConnector {
    public static void main(String[] args) {
        activityAdd();

    }

    static void accountAdd(){
        Account acc1 = new Account(123, 12345, 100.0,1000, "Test Account");
        AccountManagerImpl acConn = new AccountManagerImpl();
        acConn.createAccount(acc1);
        acConn.createAccount(123456, 765432, 12345.00, 7654, "Another test");
        acConn.disconnect();
    }

    static void activityAdd(){
        Activity act = new Activity(1234567, 12345, 100.0, new Date(), 5.0, "Test Activity");
        ActivityManagerImpl actCon = new ActivityManagerImpl();
        actCon.addActivity(act);
        actCon.disconnect();
    }
}
