package main.managers.impl;

import main.AccountType;
import main.model.Account;
import main.model.Client;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AccountManagerImplTest {

    @Test
    public void testCreateAccount() throws Exception {

        populateAccountManager();

        Client client = new Client(12345678, "Test Client", "testPassword", AccountType.GOLD, "Test address 9", "Euyfr@ufhvl.com", "054-76543", "Test Comment");
       //   TODO change the undeRscore
        ClientManagerImpl clientManager = new ClientManagerImpl() ;
        clientManager.createClient(client);

        Account account = new Account(23456789, client.getClient_id(), 1000, 100000, "Comment");
        AccountManagerImpl accountManager = new AccountManagerImpl() ;
        accountManager.createAccount(account);


        closeAllConnections(clientManager, accountManager);
    }

    private void closeAllConnections(ClientManagerImpl clientManager, AccountManagerImpl accountManager) {
        clientManager.disconnect();
        accountManager.disconnect();
    }

    private void populateAccountManager() {
        AccountManagerImpl accountManager = new AccountManagerImpl() ;
        accountManager.deleteAllAccounts();
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