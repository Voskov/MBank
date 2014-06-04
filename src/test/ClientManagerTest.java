package test;

import init.DropDb;
import init.InitiateDB;
import main.AccountType;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.model.Client;
import org.junit.*;


public class ClientManagerTest {
    private static Client client = null;
    private static ClientManagerImpl clientManager = null;

    @BeforeClass
    public static void setUpClass() {
        client = new Client("Test Name", "pasword", AccountType.GOLD, "address 6", "test@email.com", "054-1234567", "comment");
    }

    @AfterClass
    public static void tearDownClass() {
        clientManager.disconnect();
    }

    @Before
    public void setUp() {
        DropDb.dropAllTables();
        clientManager = new ClientManagerImpl();
        InitiateDB.createDb();
    }

//    @After
    public void tearDown() {
        DropDb.dropAllTables();
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
        Client dbClient = clientManager.findClient(client.getClient_id());
        Assert.assertEquals(newPassword, dbClient.getPassword());
    }

    @Test
    public void testFindById(){
        clientManager.createClient(client);
        Client dbClient = clientManager.findClient(client.getClient_id());
        Assert.assertEquals(dbClient, client);
    }

    @Test
    public void testFindByUsername(){
        clientManager.createClient(client);
        Client dbClient = clientManager.findClient(client.getClient_name());
        Assert.assertEquals(dbClient, client);
    }

    @Test
    public void testFindByClient(){
        clientManager.createClient(client);
        client.setClient_id(1);
        Client dbClient = clientManager.findClient(client);
        Assert.assertEquals(dbClient, client);
    }
}