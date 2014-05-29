package main.managers.impl;

import classes.model.Account;
import main.model.Client;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AccountManagerImplTest {

    @Test
    public void testCreateAccount() throws Exception {
        Client client = new Client(12345678, "Test Client", "testPassword", "GOLD", "Test address 9", "Euyfr@ufhvl.com", "054-76543", "Test Comment");
        Account account = new Account(23456789, 12345678, 1000, 100000, "Comment");
        ClientManagerImpl clientManager = new ClientManagerImpl() ;
        AccountManagerImpl accountManager = new AccountManagerImpl() ;
        accountManager.deleteAllAccounts();
        clientManager.createClient(client);
        clientManager.createClient(client);
        accountManager.createAccount(account);
        clientManager.disconnect();
        accountManager.disconnect();
    }

    @Test
    public void testDeleteAccount() throws Exception {

    }

    @Test
    public void testDeleteAccount1() throws Exception {

    }

    @Test
    public void testWithdrawFromAccount() throws Exception {

    }

    @Test
    public void testDepositToAccount() throws Exception {

    }
}