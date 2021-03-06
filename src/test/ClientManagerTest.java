package test;

import init.DropDb;
import init.InitiateDB;
import main.AccountType;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.db_access_layer.managers.impl.DbConnectorManagerImpl;
import main.exceptions.DbConnectorException;
import main.model.Client;
import org.junit.*;
import static org.junit.Assert.*;


public class ClientManagerTest {
    private static Client client = null;
    private static ClientManagerImpl clientManager = null;

    @Before
    public void setUp() throws DbConnectorException {

        InitiateDB.restartDb();
        clientManager = new ClientManagerImpl();
        client = new Client("Test Name", "password", AccountType.GOLD, "address 6", "test@email.com", "054-1234567", "comment");
    }

//    @After
    public void tearDown() {
        client = null;
        clientManager = null;
    }

    @Test
    public void testCreateNewClient() throws DbConnectorException {
        clientManager.createClient(client);
    }

    @Test
    public void testUpdateClient() throws DbConnectorException {
        clientManager.createClient(client);
        String newPassword = "newPassword";
        client.setPassword(newPassword);
        client.setClientId(1);
        clientManager.updateClient(client);
        Client dbClient = clientManager.findClient(client.getClientId());
        assertEquals(newPassword, dbClient.getPassword());
    }

    @Test
    public void testFindById() throws DbConnectorException {
        clientManager.createClient(client);
        client.setClientId(1);
        Client dbClient = clientManager.findClient(client.getClientId());
        assertEquals(dbClient, client);
    }

    @Test
    public void testFindByUsername() throws DbConnectorException {
        clientManager.createClient(client);
        client.setClientId(1);
        Client dbClient = clientManager.findClient(client.getClientName());
        assertEquals(dbClient, client);
    }

    @Test
    public void testFindByClient() throws DbConnectorException {
        clientManager.createClient(client);
        client.setClientId(1);
        Client dbClient = clientManager.findClient(client);
        assertEquals(dbClient, client);
    }
}