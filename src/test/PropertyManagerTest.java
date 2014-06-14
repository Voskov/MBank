package main.db_access_layer.managers;

import init.InitiateDB;
import junit.framework.Assert;
import main.AccountType;
import main.db_access_layer.managers.PropertyManager;
import main.db_access_layer.managers.impl.PropertyManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertEquals(0.5, commission);
        double gold_deposit_credit = pm.getProperty("gold_deposit_credit");
        Assert.assertEquals(1000000.0, gold_deposit_credit);
    }

    @Test
    public void testSetProperty() throws Exception {
        double newCommission = 1.5;
        pm.setProperty("commission_rate", String.valueOf(newCommission));
        double commission = pm.getProperty("commission_rate");
        Assert.assertEquals(newCommission, commission);
    }

    @Test
    public void testNewGetProp() throws Exception {
        Assert.assertEquals(0.015, pm.getProp(AccountType.REGULAR, "deposit_commission"));
        Assert.assertEquals(1000000D, pm.getProp(AccountType.GOLD, "deposit_credit"));
    }
}