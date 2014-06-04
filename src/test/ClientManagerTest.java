package test;

import init.DropTables;
import init.InitiateDB;
import main.AccountType;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.model.Client;
import org.junit.*;

public class ClientManagerTest {
    private static Client client = null;
    private static ClientManagerImpl clientManager = null;

    @BeforeClass
    public static void beforeClass() {
        client = new Client("Test Name", "pasword", AccountType.GOLD, "address 6", "test@email.com", "054-1234567", "comment");
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
        Client dbClient = clientManager.findClient(client);
        Assert.assertEquals(dbClient, client);
    }

    @Test
    public void testGenerateId(){
        clientManager.createClient(client);
        long id = clientManager.generateId();
        System.out.println(id);
    }
}