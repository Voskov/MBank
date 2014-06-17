package test;

import init.InitiateDB;
import main.AccountType;
import main.db_access_layer.managers.PropertyManager;
import main.db_access_layer.managers.impl.PropertyManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyManagerTest {
    PropertyManager pm = null;

    @Before
    public void setUp() throws Exception {
        InitiateDB.restartDb();
        pm = new PropertyManagerImpl();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetProperty() throws Exception {
        double commission = pm.getProperty("commission_rate");
        assertEquals(0.5, commission);
        double gold_deposit_credit = pm.getProperty("gold_deposit_credit");
        assertEquals(1000000.0, gold_deposit_credit, 0.001);
    }

    @Test
    public void testSetProperty() throws Exception {
        double newCommission = 1.5;
        pm.setProperty("commission_rate", String.valueOf(newCommission));
        double commission = pm.getProperty("commission_rate");
        assertEquals(newCommission, commission, 0.001);
    }

    @Test
    public void testNewGetProp() throws Exception {
        assertEquals(0.015, pm.getProperty(AccountType.REGULAR, "deposit_commission"), 0.001);
        assertEquals(1000000D, pm.getProperty(AccountType.GOLD, "deposit_credit"), 0.001);
    }
}