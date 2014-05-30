package test;

import connect.DropTables;
import connect.InitiateDB;
import main.AccountType;
import main.managers.impl.ClientManagerImpl;
import main.model.Client;
import org.junit.*;

public class ClientManagerTest {
    private static Client client = null;
    private static ClientManagerImpl clientManager = null;

    @BeforeClass
    public static void beforeClass() {
        client = new Client(1234567, "Test Name", "pasword", AccountType.GOLD, "address 6", "test@email.com", "054-1234567", "comment");

    }

    @AfterClass
    public static void afterClass() {
        clientManager.disconnect();
    }

    @Before
    public void before() {
        DropTables.dropAllTables();
        clientManager = new ClientManagerImpl();
        InitiateDB.createDb();
    }

//    @After
    public void after() {
        DropTables.dropAllTables();
        InitiateDB.createDb();
    }

    @Test
    public void testCreateNewClient() {
        clientManager.createClient(client);
    }

    @Test
    public void testUpdateClient() {
        clientManager.createClient(client);
        String newPassword = "newPassword";
        client.setPassword(newPassword);
        clientManager.updateClient(client);
        Client dbClient = clientManager.findClientById(client.getClient_id());
        Assert.assertEquals(newPassword, dbClient.getPassword());
    }

    @Test
    public void testFindById(){
        clientManager.createClient(client);
        Client dbClient = clientManager.findClientById(client.getClient_id());
        Assert.assertTrue(dbClient.equals(client));
        Assert.assertEquals(dbClient, client);
    }

    @Test
    public void testFindByUsername(){
        clientManager.createClient(client);
        Client dbClient = clientManager.findClientByClientName(client.getClient_name());
        Assert.assertTrue(dbClient.equals(client));
        Assert.assertEquals(dbClient, client);

    }


}