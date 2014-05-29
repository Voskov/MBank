package test;

import connect.DropTables;
import connect.InitiateDB;
import main.AccountType;
import main.managers.impl.ClientManagerImpl;
import main.model.Client;
import org.junit.*;

public class ClientManagerTest {
    Client client = null;
    ClientManagerImpl clientManager = null;

    @BeforeClass
    public void beforeClass() {
        clientManager = new ClientManagerImpl();
    }

    @AfterClass
    public void afterClass() {
        clientManager.disconnect();
    }

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
    }

    @Test
    public void testCreateNewUser() {
        clientManager.createClient(client);
    }

    @Ignore
    private Client populateClient() {
        cleanClientTable();
        ClientManagerImpl clientManager = new ClientManagerImpl();

        clientManager.createClient(client);
        return client;
    }

    @Ignore
    private void cleanClientTable() {
        ClientManagerImpl clientManager = new ClientManagerImpl();
        clientManager.deleteAllClients();
    }

    @Ignore
    public void testSameClient() {
        Client client = populateClient();
        ClientManagerImpl clientManager = new ClientManagerImpl();
        clientManager.createClient(client);
        clientManager.createClient(client);
    }
}