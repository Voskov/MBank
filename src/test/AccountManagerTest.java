package test;

import main.AccountType;
import main.managers.impl.AccountManagerImpl;
import main.managers.impl.ClientManagerImpl;
import main.model.Account;
import main.model.Client;
import org.junit.*;


public class AccountManagerTest extends AbstractTest{
    ClientManagerImpl clientManager = null;
    AccountManagerImpl accountManager = null;
    Client client = null;
    Account account = null;

    @BeforeClass
    public void beforeClass(){
        client = new Client(12345678, "Test Client", "testPassword", AccountType.GOLD, "Test address 9", "Euyfr@ufhvl.com", "054-76543", "Test Comment");
        account = new Account(23456789, 12345678, 1000, 100000, "Comment");

        clientManager = new ClientManagerImpl() ;
        accountManager = new AccountManagerImpl() ;
    }

    @Before
    public void before(){
        System.out.println("Before");
        dropTables();
        createTables();

        clientManager.createClient(client);
        accountManager.createAccount(account);

    }

    @After
    public void after(){
        System.out.println("After");
//        dropTables();
//        createTables();
    }


    @Test
    public void testDeleteAccountById() throws Exception {
        accountManager.deleteAccount(account.getAccount_id());
    }

    @Test void deleteAccountByAccount(){
        accountManager.deleteAccount(account);
    }

    @Ignore
    public void testDeleteAccount1() throws Exception {

    }

    @Ignore
    public void testWithdrawFromAccount() throws Exception {

    }

    @Ignore
    public void testDepositToAccount() throws Exception {

    }
}