package test;

import init.InitiateDB;
import main.AccountType;
import main.db_access_layer.managers.AccountManager;
import main.db_access_layer.managers.ActivityManager;
import main.db_access_layer.managers.ClientManager;
import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.db_access_layer.managers.impl.ActivityManagerImpl;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.model.Account;
import main.model.Activity;
import main.model.Client;
import main.services.AdminAction;
import main.services.impl.AdminActionImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AdminActionImplTest {
    AdminAction aa;

    ClientManager cm;
    AccountManager am;
    ActivityManager acm;

    Client testClient;
    Account testAccount;
    Activity testActivity;


    @Before
    public void setUp()  {
        InitiateDB.restartDb();
        aa = new AdminActionImpl();

        cm = new ClientManagerImpl();
        am = new AccountManagerImpl();
        acm = new ActivityManagerImpl();

        Date today = new Date();
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);

        testClient = new Client(1, "test Client", "qwerty", AccountType.REGULAR, "qwertyui fgh jkl", "wertyui@lkjhgfd.com", "876543", "Test client");
        testAccount = new Account(1, 1, 10000, 0, "Initial account");
        testActivity = new Activity(1, 1, 10000, today, 0, "Account created");
    }

    @After
    public void tearDown()  {

    }

    @Test
    public void testAddNewClient()  {
        aa.addNewClient(testClient, 10000);
        Client dbClient = cm.findClient(testClient);
        Account dbAccount = am.findAccount(testAccount);
        Activity dbActivity = acm.findActivity(1);


        assertEquals(testClient, dbClient);
        assertEquals(testAccount, dbAccount);
        assertEquals(testActivity, dbActivity);
    }

    @Test
    public void testRemoveClient()  {
        aa.addNewClient(testClient, 10000);
        aa.removeClient(testClient);

        Client dbClient = cm.findClient(1);
        HashSet<Account> dbAccounts = am.allClientsAccounts(1);

        assertNull(dbClient);
        assertTrue(dbAccounts.isEmpty());   // fails here
    }

    @Test
    public void testRemoveClient1()  {
        aa.addNewClient(testClient, 10000);
        aa.removeClient(1);

        Client dbClient = cm.findClient(1);
        HashSet<Account> dbAccounts = am.allClientsAccounts(1);

        assertNull(dbClient);
        assertTrue(dbAccounts.isEmpty());   // fails here

    }

    @Test
    public void testCreateNewAccount()  {

    }

    @Test
    public void testRemoveAccount()  {

    }

    @Test
    public void testViewAllClientsDetails()  {

    }

    @Test
    public void testViewAllAccountsDetails()  {

    }

    @Test
    public void testViewAllDepositsDetails()  {

    }

    @Test
    public void testViewAllActivitiesDetails()  {

    }

    @Test
    public void testViewAllActivitiesDetails1()  {

    }

    @Test
    public void testUpdateClientDetails()  {

    }

    @Test
    public void testViewClientDetails()  {

    }

    @Test
    public void testViewClientDetails1()  {

    }

    @Test
    public void testViewAccountDetails()  {

    }

    @Test
    public void testViewAccountDetails1()  {

    }

    @Test
    public void testViewClientDeposits()  {

    }

    @Test
    public void testUpdateSystemProperty()  {

    }

    @Test
    public void testUpdateSystemProperty1()  {

    }

    @Test
    public void testUpdateSystemProperty2()  {

    }

    @Test
    public void testUpdateSystemProperty3()  {

    }

    @Test
    public void testViewSystemProperty()  {

    }

    @Test
    public void testViewSystemProperty1()  {

    }
}