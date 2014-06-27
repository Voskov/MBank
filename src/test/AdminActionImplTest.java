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
    public void setUp() throws Exception {
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
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddNewClient() throws Exception {
        aa.addNewClient(testClient, 10000);
        Client dbClient = cm.findClient(testClient);
        Account dbAccount = am.findAccount(testAccount);
        Activity dbActivity = acm.findActivity(1);


        assertEquals(testClient, dbClient);
        assertEquals(testAccount, dbAccount);
        assertEquals(testActivity, dbActivity);
    }

    @Test
    public void testRemoveClient() throws Exception {
        aa.addNewClient(testClient, 10000);
        aa.removeClient(testClient);

        Client dbClient = cm.findClient(1);
        HashSet<Account> dbAccounts = am.allClientsAccounts(1);

        assertNull(dbClient);
        assertTrue(dbAccounts.isEmpty());   // fails here
    }

    @Test
    public void testRemoveClient1() throws Exception {
        aa.addNewClient(testClient, 10000);
        aa.removeClient(1);

        Client dbClient = cm.findClient(1);
        HashSet<Account> dbAccounts = am.allClientsAccounts(1);

        assertNull(dbClient);
        assertTrue(dbAccounts.isEmpty());   // fails here

    }

    @Test
    public void testCreateNewAccount() throws Exception {

    }

    @Test
    public void testRemoveAccount() throws Exception {

    }

    @Test
    public void testViewAllClientsDetails() throws Exception {

    }

    @Test
    public void testViewAllAccountsDetails() throws Exception {

    }

    @Test
    public void testViewAllDepositsDetails() throws Exception {

    }

    @Test
    public void testViewAllActivitiesDetails() throws Exception {

    }

    @Test
    public void testViewAllActivitiesDetails1() throws Exception {

    }

    @Test
    public void testUpdateClientDetails() throws Exception {

    }

    @Test
    public void testViewClientDetails() throws Exception {

    }

    @Test
    public void testViewClientDetails1() throws Exception {

    }

    @Test
    public void testViewAccountDetails() throws Exception {

    }

    @Test
    public void testViewAccountDetails1() throws Exception {

    }

    @Test
    public void testViewClientDeposits() throws Exception {

    }

    @Test
    public void testUpdateSystemProperty() throws Exception {

    }

    @Test
    public void testUpdateSystemProperty1() throws Exception {

    }

    @Test
    public void testUpdateSystemProperty2() throws Exception {

    }

    @Test
    public void testUpdateSystemProperty3() throws Exception {

    }

    @Test
    public void testViewSystemProperty() throws Exception {

    }

    @Test
    public void testViewSystemProperty1() throws Exception {

    }
}