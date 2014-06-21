package test;

import init.InitiateDB;
import main.AccountType;
import main.DepositType;
import main.db_access_layer.managers.AccountManager;
import main.db_access_layer.managers.ClientManager;
import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.model.Account;
import main.model.Client;
import main.model.Deposit;
import main.services.AdminAction;
import main.services.ClientAction;
import main.services.impl.AdminActionImpl;
import main.services.impl.ClientActionImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ClientActionTest {
    Client testClient;
    Account testAccount;
    Deposit testDeposit;
    ClientAction ca;
    ClientManager cm;
    AccountManager am;

    @Before
    public void setUp() throws Exception {
        InitiateDB.restartDb();
        testClient = new Client(1, "test Client", "qwerty", AccountType.REGULAR, "qwertyui fgh jkl", "wertyui@lkjhgfd.com", "876543", "Test client");
        testAccount = new Account(1, 1, 1000, 10000, "Test Account");
        ca = new ClientActionImpl();
        cm = new ClientManagerImpl();
        am = new AccountManagerImpl();
        cm.createClient(testClient);
        am.createAccount(testAccount);
    }

//    @Test
    public void testWithdrawFromAccount() throws Exception {
        ca.withdrawFromAccount(testAccount, 100);
        Account dbAccount = am.findAccount(1);
        assertEquals(899.5, dbAccount.getBalance(), 0.0001);
    }

//    @Test
    public void testWithdrawFromAccount1() throws Exception {
        ca.withdrawFromAccount(1, 100);
        Account dbAccount = am.findAccount(1);
        assertEquals(899.5, dbAccount.getBalance(), 0.0001);

    }

//    @Test
    public void testWithdrawFromAccount2() throws Exception {
        ca.withdrawFromAccount(testClient, 100);
        Account dbAccount = am.findAccount(1);
        assertEquals(899.5, dbAccount.getBalance(), 0.0001);
    }

//    @Test
    public void testDepositToAccount() throws Exception {
        ca.depositToAccount(testClient, 100);
        Account dbAccount = am.findAccount(1);
        assertEquals(1099.5, dbAccount.getBalance(), 0.0001);
    }

//    @Test
    public void testDepositToAccount1() throws Exception {
        ca.depositToAccount(1, 100);
        Account dbAccount = am.findAccount(1);
        assertEquals(1099.5, dbAccount.getBalance(), 0.0001);
    }

//    @Test
    public void testDepositToAccount2() throws Exception {
        ca.depositToAccount(testAccount, 100);
        Account dbAccount = am.findAccount(1);
        assertEquals(1099.5, dbAccount.getBalance(), 0.0001);
    }

//    @Test
    public void testCreateNewDeposit() throws Exception {

    }

    //@Test
    public void testCreateNewDeposit1() throws Exception {

    }

    //@Test
    public void testPreOpenDeposit() throws Exception {

    }

    //@Test
    public void testUpdateClientDetails() throws Exception {

    }

    //@Test
    public void testViewClientDeposits() throws Exception {

    }

    //@Test
    public void testViewClientActivities() throws Exception {

    }
}