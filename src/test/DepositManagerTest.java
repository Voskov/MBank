package test;

import init.DropDb;
import init.InitiateDB;
import main.DepositType;
import main.db_access_layer.managers.DepositManager;
import main.db_access_layer.managers.impl.DepositManagerImpl;
import main.model.Deposit;
import org.junit.*;

import java.sql.ResultSet;
import java.sql.Statement;
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
        Deposit testDeposit = new Deposit(1, 12345, 1000.0, DepositType.SHORT, 1010, new Date(1401896199000L), new Date(1402906199000L));
        depositManager.createNewDeposit(testDeposit);
    }

//    @Test
//    public void testBuildDeposit(){
//        Deposit testDeposit = new Deposit(1, 12345, 1000.0, DepositType.SHORT, 1010, new Date(1401896199000L), new Date(1402906199000L));
//        depositManager.createNewDeposit(testDeposit);
//        StringBuilder sqlStrBldr = new StringBuilder("SELECT * FROM Deposits WHERE deposit_id=").append(testDeposit.getDepositId());
//        Statement stmt = null;
//        ResultSet res = stmt.executeQuery(sqlStrBldr.toString());
//    }

    @Test
    public void testFindDepositById() {
        Deposit testDeposit = new Deposit(1, 12345, 1000.0, DepositType.SHORT, 1010, new Date(1401896199000L), new Date(1402906199000L));
        depositManager.createNewDeposit(testDeposit);
        Deposit dbDeposit = depositManager.findDeposit(testDeposit.getDepositId());
        Assert.assertEquals(testDeposit, dbDeposit);
    }

    @Test
    public void testFindDepositByDeposit() {
        Deposit testDeposit = new Deposit(1, 12345, 1000.0, DepositType.SHORT, 1010, new Date(1401896199000L), new Date(1402906199000L));
        depositManager.createNewDeposit(testDeposit);
        Deposit dbDeposit = depositManager.findDeposit(testDeposit);
        Assert.assertTrue(testDeposit.equals(dbDeposit));
    }

}