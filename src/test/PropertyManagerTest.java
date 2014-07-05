package test;

import init.InitiateDB;
import main.AccountType;
import main.db_access_layer.managers.ConnectionPool;
import main.db_access_layer.managers.PropertyManager;
import main.db_access_layer.managers.impl.ConnectionPoolImpl;
import main.db_access_layer.managers.impl.PropertyManagerImpl;
import main.exceptions.DbConnectorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PropertyManagerTest {
    PropertyManager pm = null;

    @Before
    public void setUp()  {
        InitiateDB.restartDb();
        pm = new PropertyManagerImpl();
        ConnectionPool pool = new ConnectionPoolImpl();
    }

    @After
    public void tearDown()  {
        pm = null;
    }

    @Test
    public void testGetProperty()  {
        double commission = pm.getProperty("commission_rate");
        assertEquals(0.5, commission, 0.001);
        double gold_deposit_credit = pm.getProperty("gold_deposit_credit");
        assertEquals(1000000.0, gold_deposit_credit, 0.001);
    }

    @Test
    public void testSetProperty()  {
        double newCommission = 1.5;
        pm.setProperty("commission_rate", String.valueOf(newCommission));
        double commission = pm.getProperty("commission_rate");
        assertEquals(newCommission, commission, 0.001);
    }

    @Test
    public void testNewGetProp()  {
        assertEquals(0.015, pm.getProperty(AccountType.REGULAR, "deposit_commission"), 0.001);
        assertEquals(1000000D, pm.getProperty(AccountType.GOLD, "deposit_credit"), 0.001);
    }

    @Test
    public void testGetCredentials() throws SQLException, DbConnectorException {
        HashMap<String, String> dbCredentials = pm.getAdminCredentials();
        HashMap<String, String> testCredentials = new HashMap<String, String>();
        testCredentials.put("admin_username", "system");
        testCredentials.put("admin_password", "admin");
        assertEquals(testCredentials, dbCredentials);
    }
}