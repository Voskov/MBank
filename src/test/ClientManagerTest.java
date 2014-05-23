package test;

import connect.DropTables;
import connect.InitiateDB;
import junit.framework.Assert;
import main.AccountType;
import main.managers.impl.ClientManagerImpl;
import main.model.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientManagerTest {
    Client client = null;
    ClientManagerImpl clientManager = null;

    @Before
    public void before() {
        DropTables.dropAllTables();
        InitiateDB.createDb();

        client = new Client(1234567, "Test Name", "pasword", AccountType.GOLD, "address 6", "test@email.com", "054-1234567", "comment");
    }

    @After
    public void after() {
        DropTables.dropAllTables();
        InitiateDB.createDb();
        clientManager.disconnect();
    }

    @Test
    public void testCreateNewUser() {
        clientManager = new ClientManagerImpl();
        Client new_client = clientManager.findById(client.getClient_id());
        Assert.assertNotNull(new_client);
        Assert.assertEquals(client.getClient_name(), new_client.getClient_name());
        Assert.assertEquals(client.getEmail(), new_client.getEmail());

        cleanClientTable();
    }

    private Client populateClient() {
        cleanClientTable();
        ClientManagerImpl clientManager = new ClientManagerImpl();

        clientManager.createClient(client);
        return client;
    }

    private void cleanClientTable() {
        ClientManagerImpl clientManager = new ClientManagerImpl();
        clientManager.deleteAllClients();
    }

    @Test
    public void testSameClient() {
        Client client = populateClient();
        ClientManagerImpl clientManager = new ClientManagerImpl();
        clientManager.createClient(client);
        clientManager.createClient(client);
    }
}