package main.services;

import init.InitiateDB;
import main.AccountType;
import main.model.Account;
import main.model.Client;
import main.services.impl.ClientActionImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientActionTest {
    Client testClient;
    Account testAccount;
    ClientAction ca;
    @Before
    public void setUp() throws Exception {
        InitiateDB.restartDb();
        testClient = new Client(1, "test Client", "wertyuio", AccountType.REGULAR, "qwertyui fgh jkl", "wertyui@lkjhgfd.com", "876543", "Test client");
        testAccount = new Account(1, 1, 1000, 10000, "Test Account");
        ca = new ClientActionImpl();

    }

    @Test
    public void testWithdrawFromAccount() throws Exception {
        ca.withdrawFromAccount(testAccount, 100);
    }

    @Test
    public void testWithdrawFromAccount1() throws Exception {
        ca.withdrawFromAccount(1, 100);
    }

    @Test
    public void testWithdrawFromAccount2() throws Exception {
        ca.withdrawFromAccount(testClient, 100);
    }

    @Test
    public void testDepositToAccount() throws Exception {

    }

    @Test
    public void testDepositToAccount1() throws Exception {

    }

    @Test
    public void testDepositToAccount2() throws Exception {

    }

    @Test
    public void testCreateNewDeposit() throws Exception {

    }

    @Test
    public void testCreateNewDeposit1() throws Exception {

    }

    @Test
    public void testPreOpenDeposit() throws Exception {

    }

    @Test
    public void testUpdateClientDetails() throws Exception {

    }

    @Test
    public void testViewClientDeposits() throws Exception {

    }

    @Test
    public void testViewClientActivities() throws Exception {

    }
}