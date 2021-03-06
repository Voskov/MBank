package test;

import init.DropDb;
import init.InitiateDB;
import main.DepositType;
import main.db_access_layer.managers.DepositManager;
import main.db_access_layer.managers.impl.DepositManagerImpl;
import main.exceptions.DbConnectorException;
import main.model.Deposit;
import org.junit.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import static org.junit.Assert.*;

public class DepositManagerTest extends AbstractTest {
    static DepositManagerImpl depositManager = null;

    @Before
    public void setUp() throws DbConnectorException {
        InitiateDB.restartDb();
        depositManager = new DepositManagerImpl();
    }

    @After
    public void tearDown() throws DbConnectorException {
        depositManager.getPool().drainConnectionPool();
        depositManager = null;
    }

    @Test
    public void testCreateDeposit() throws DbConnectorException {
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
    public void testFindDepositById() throws DbConnectorException {
        Calendar cal = new GregorianCalendar(2014, 06, 04);
        Deposit testDeposit = new Deposit(1, 12345, 1000.0, DepositType.SHORT, 1010, new Date(2014, 06, 04), new Date(2014, 06, 14));
        depositManager.createNewDeposit(testDeposit);
        Deposit dbDeposit = depositManager.findDeposit(testDeposit.getDepositId());
        assertTrue(testDeposit.equals(dbDeposit));
    }

    @Test
    public void testFindDepositByDeposit() throws DbConnectorException {
        Deposit testDeposit = new Deposit(1, 12345, 1000.0, DepositType.SHORT, 1010, new Date(1401896199000L), new Date(1402906199000L));
        depositManager.createNewDeposit(testDeposit);
        Deposit dbDeposit = depositManager.findDeposit(testDeposit);
        assertTrue(testDeposit.equals(dbDeposit));
    }

    @Test
    public void testGetExpired() throws DbConnectorException {
        Deposit expiredDeposit = new Deposit(1, 12345, 1000.0, DepositType.SHORT, 1010, new Date(1400896199000L), new Date(1400906199000L));
        Deposit notExpiredDeposit = new Deposit(2, 12345, 1000.0, DepositType.SHORT, 1010, new Date(1401896199000L), new Date(1403906199000L));
        depositManager.createNewDeposit(expiredDeposit);
        depositManager.createNewDeposit(notExpiredDeposit);
        HashSet<Deposit> testSet = new HashSet<Deposit>();
        testSet.add(expiredDeposit);
        HashSet expiredDeposits = depositManager.allExpiredDeposits();
        Iterator<Deposit> it = expiredDeposits.iterator();
        Deposit dbDeposit = it.next();
        assertEquals(expiredDeposit, dbDeposit);
        assertTrue(expiredDeposit.equals(dbDeposit));
    }

    @Ignore
    public void testDate() {
        Deposit testDeposit = new Deposit(1, 12345, 1000.0, DepositType.SHORT, 1010, new Date(1400896199000L), new Date(1400901234567L));
        System.out.println(testDeposit.getClosingDate());
    }
}