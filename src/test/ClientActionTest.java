package test;

import init.InitiateDB;
import main.AccountType;
import main.DepositType;
import main.db_access_layer.managers.AccountManager;
import main.db_access_layer.managers.ClientManager;
import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.exceptions.ClientException;
import main.exceptions.DbConnectorException;
import main.model.Account;
import main.model.Client;
import main.model.Deposit;
import main.services.AdminAction;
import main.services.ClientAction;
import main.services.impl.AdminActionImpl;
import main.services.impl.ClientActionImpl;
import org.junit.Before;
import org.junit.Test;

import javax.security.auth.login.AccountException;
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
    public void setUp() throws DbConnectorException {
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
    public void testWithdrawFromAccount() throws ClientException, DbConnectorException {
        ca.withdrawFromAccount(testAccount, 100);
        Account dbAccount = am.findAccount(1);
        assertEquals(899.5, dbAccount.getBalance(), 0.0001);
    }

//    @Test
    public void testWithdrawFromAccount1() throws ClientException, DbConnectorException {
        ca.withdrawFromAccount(1, 100);
        Account dbAccount = am.findAccount(1);
        assertEquals(899.5, dbAccount.getBalance(), 0.0001);

    }

//    @Test
    public void testWithdrawFromAccount2() throws ClientException, DbConnectorException {
        ca.withdrawFromAccount(testClient, 100);
        Account dbAccount = am.findAccount(1);
        assertEquals(899.5, dbAccount.getBalance(), 0.0001);
    }

//    @Test
    public void testDepositToAccount() throws DbConnectorException, ClientException, AccountException {
        ca.depositToAccount(testClient, 100);
        Account dbAccount = am.findAccount(1);
        assertEquals(1099.5, dbAccount.getBalance(), 0.0001);
    }

//    @Test
    public void testDepositToAccount1() throws AccountException, DbConnectorException {
        ca.depositToAccount(1, 100);
        Account dbAccount = am.findAccount(1);
        assertEquals(1099.5, dbAccount.getBalance(), 0.0001);
    }

//    @Test
    public void testDepositToAccount2() throws AccountException, DbConnectorException {
        ca.depositToAccount(testAccount, 100);
        Account dbAccount = am.findAccount(1);
        assertEquals(1099.5, dbAccount.getBalance(), 0.0001);
    }

//    @Test
    public void testCreateNewDeposit()  {

    }

    //@Test
    public void testCreateNewDeposit1()  {

    }

    //@Test
    public void testPreOpenDeposit()  {

    }

    //@Test
    public void testUpdateClientDetails()  {

    }

    //@Test
    public void testViewClientDeposits()  {

    }

    //@Test
    public void testViewClientActivities()  {

    }
}