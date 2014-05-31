package test;

import connect.DropTables;
import connect.InitiateDB;
import main.AccountType;
import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.model.Account;
import main.model.Client;
import org.junit.*;


public class AccountManagerTest {
    private static ClientManagerImpl clientManager = null;
    private static AccountManagerImpl accountManager = null;
    private static Client testClient = null;
    private static Account testAccount = null;

    @BeforeClass
    public static void beforeClass() {
        testClient = new Client(12345678, "Test Client", "testPassword", AccountType.GOLD, "Test address 9", "Euyfr@ufhvl.com", "054-76543", "Test Comment");
        testAccount = new Account(23456789, 12345678, 1000, 100000, "Comment");


    }

    @AfterClass
    public static void afterClass() {
        clientManager.disconnect();
        accountManager.disconnect();
    }

    @Before
    public void before() {
        DropTables.dropAllTables();
        InitiateDB.createDb();
        clientManager = new ClientManagerImpl();
        accountManager = new AccountManagerImpl();
        clientManager.createClient(testClient);
        accountManager.createAccount(testAccount);
    }

    @After
    public void after() {
    }


    @Test
    public void testDeleteAccountById() throws Exception {
        accountManager.deleteAccount(testAccount.getAccount_id());
    }

    @Test
    public void deleteAccountByAccount() {
        accountManager.deleteAccount(testAccount);
    }

}