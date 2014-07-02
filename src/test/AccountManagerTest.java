package test;

import init.DropDb;
import init.InitiateDB;
import main.db_access_layer.managers.AccountManager;
import main.db_access_layer.managers.DbConnectorManager;
import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.db_access_layer.managers.impl.DbConnectorManagerImpl;
import main.exceptions.DbConnectorException;
import main.model.Account;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;


import java.util.HashSet;

public class AccountManagerTest extends AbstractTest {

    public static AccountManager accountManager = null;
    public static DbConnectorManagerImpl dbConnectorManager = null;

    @BeforeClass
    public static void classSetUp() {
        try {
            dbConnectorManager = new DbConnectorManagerImpl();
            dbConnectorManager.initiateConnectionPool();
            accountManager = new AccountManagerImpl();
        } catch (DbConnectorException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void classTearDown() throws DbConnectorException {
        dbConnectorManager.drainConnectionPool();
    }

    @Before
    public void setUp() throws DbConnectorException {
//        accountManager = new AccountManagerImpl();
        DropDb.dropAllTables();
        InitiateDB.createDb();
    }


    @Test
    public void testDeleteAccountById() throws Exception {
        Account testAccount = new Account(1, 12345678, 1000, 100000, "Comment");
        accountManager.createAccount(testAccount);

        accountManager.deleteAccount(testAccount.getAccountId());
        assertEquals(0, accountManager.countAllAccounts());
    }

    @Test
    public void testDeleteAccount() throws DbConnectorException {
        Account testAccount = new Account(1, 12345678, 1000, 100000, "Comment");
        accountManager.createAccount(testAccount);

        accountManager.deleteAccount(testAccount);
        assertEquals(0, accountManager.countAllAccounts());
    }

    @Test
    public void testFindAccount() throws Exception {
        Account testAccount = new Account(1, 12345678, 1000, 100000, "Comment");
        accountManager.createAccount(testAccount);

        Account dbAccount = accountManager.findAccount(testAccount);
        assertTrue(testAccount.equals(dbAccount));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        Account testAccount = new Account(1, 12345678, 1000, 100000, "Comment");
        accountManager.createAccount(testAccount);

        double newBalance = 7263;
        testAccount.setBalance(newBalance);
        accountManager.updateAccount(testAccount);
        Account dbAccount = accountManager.findAccount(testAccount);
        assertTrue(testAccount.equals(dbAccount));
    }

    @Test
    public void testGetAllAccountsByClientId() throws DbConnectorException {
        long client_id = 12345678;
        HashSet<Account> testAccountsSet = new HashSet<Account>();

        testAccountsSet.add(new Account(1, client_id, 1000, 100000, "Comment"));
        testAccountsSet.add(new Account(2, client_id, 1000, 100000, "Comment"));
        testAccountsSet.add(new Account(3, client_id, 1000, 100000, "Comment"));
        for (Account account : testAccountsSet) {
            accountManager.createAccount(account);
        }

        HashSet<Account> allDbAccounts = accountManager.allClientsAccounts(client_id);
        assertTrue(testAccountsSet.retainAll(allDbAccounts));
    }
}