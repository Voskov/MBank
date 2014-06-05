package test;

import init.DropDb;
import init.InitiateDB;
import main.db_access_layer.managers.AccountManager;
import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.model.Account;
import main.model.Client;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashSet;

public class AccountManagerTest extends AbstractTest {

    public static AccountManager accountManager = null;


    @Before
    public void setUp() {
        accountManager = new AccountManagerImpl();
        DropDb.dropAllTables();
        InitiateDB.createDb();
    }


    @Test
    public void testDeleteAccountById() throws Exception {
        Account testAccount = new Account(1, 12345678, 1000, 100000, "Comment");
        accountManager.createAccount(testAccount);

        accountManager.deleteAccount(testAccount.getAccount_id());
        Assert.assertEquals(0, accountManager.countAllAccounts());
    }

    @Test
    public void testDeleteAccount() {
        Account testAccount = new Account(1, 12345678, 1000, 100000, "Comment");
        accountManager.createAccount(testAccount);

        accountManager.deleteAccount(testAccount);
        Assert.assertEquals(0, accountManager.countAllAccounts());
    }

    @Test
    public void testFindAccount() {
        Account testAccount = new Account(1, 12345678, 1000, 100000, "Comment");
        accountManager.createAccount(testAccount);

        Account dbAccount = accountManager.findAccount(testAccount);
        Assert.assertTrue(testAccount.equals(dbAccount));
    }

    @Test
    public void testUpdateAccount() {
        Account testAccount = new Account(1, 12345678, 1000, 100000, "Comment");
        accountManager.createAccount(testAccount);

        double newBalance = 7263;
        testAccount.setBalance(newBalance);
        accountManager.updateAccount(testAccount);
        Account dbAccount = accountManager.findAccount(testAccount);
        Assert.assertTrue(testAccount.equals(dbAccount));
    }

    @Test
    public void testGetAllAccountsByClientId(){
        long client_id = 12345678;
        HashSet<Account> testAccountsSet = new HashSet<Account>();

        testAccountsSet.add(new Account(1, client_id, 1000, 100000, "Comment"));
        testAccountsSet.add(new Account(2, client_id, 1000, 100000, "Comment"));
        testAccountsSet.add(new Account(3, client_id, 1000, 100000, "Comment"));
        for (Account account: testAccountsSet){
            accountManager.createAccount(account);
        }

        HashSet<Account> allDbAccounts = accountManager.allClientsAccounts(client_id);
        Assert.assertTrue(testAccountsSet.retainAll(allDbAccounts));
    }
}