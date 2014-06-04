package test;

import init.DropDb;
import init.InitiateDB;
import main.DepositType;
import main.db_access_layer.managers.DepositManager;
import main.db_access_layer.managers.impl.DepositManagerImpl;
import main.model.Deposit;
import org.junit.*;

import java.util.Date;

public class DepositManagerTest extends AbstractTest {
    static DepositManagerImpl depositManager = null;

    @BeforeClass
    public static void beforeClass(){
        depositManager = new DepositManagerImpl();
    }
    @Before
    public void setUp() {
        depositManager = new DepositManagerImpl();
        DropDb.dropAllTables();
        InitiateDB.createDb();
    }

    @Test
    public void testCreateDeposit() {
        Deposit testDeposit = new Deposit(1, 12345, 1000.0, DepositType.SHORT, 1010, new Date(1401896199000L), new Date(1401906199000L));
        depositManager.createNewDeposit(testDeposit);
    }


}