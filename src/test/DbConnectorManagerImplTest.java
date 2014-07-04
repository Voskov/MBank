package test;

import config.ImportDbSettings;
import main.db_access_layer.managers.impl.DbConnectorManagerImpl;
import main.exceptions.DbConnectorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.Properties;

import static org.junit.Assert.*;

public class DbConnectorManagerImplTest {
    DbConnectorManagerImpl dbCon;
    protected static Properties prop = ImportDbSettings.loadDbProperties();   // load DB properties from the config file
    int defaultConnectionsAmount = Integer.parseInt(prop.getProperty("DEFAULT_CONNECTIONS_AMOUNT"));  //50
    int maximumAmountOfConections = Integer.parseInt(prop.getProperty("MAXIMUM_CONNECTIONS_AMOUNT"));  //100

    @Before
    public void setUp() throws Exception {
        dbCon = new DbConnectorManagerImpl();
        dbCon.initiateConnectionPool();

    }

    @After
    public void tearDown() throws DbConnectorException {
        dbCon.drainConnectionPool();
        dbCon = null;
    }

    @Test
    public void testGetConnection() throws Exception {
        assertEquals(dbCon.connectionsPool.size(), 50);
        Connection con = dbCon.getConnection();
        assertEquals(dbCon.connectionsPool.size(), 49);
    }

    @Test
    public void testReturnConnection() throws Exception {
        Connection con = dbCon.getConnection();
    }

    @Test
    public void testDrainPool() throws Exception {
        dbCon.drainConnectionPool();
        assertEquals(0, dbCon.connectionsPool.size());
        assertEquals(0, dbCon.connectionsInUse.size());
    }


    @Test
    public void testExtendPool() throws Exception {
        for (int i = 0; i < defaultConnectionsAmount + 5; i++) {
            Connection con = dbCon.getConnection();
        }
        assertTrue("Should be able to take mote connections than the default", true);
    }

    @Test
    public void testTooManyConnections() throws Exception {
        for (int i = 0; i < maximumAmountOfConections + 5; i++) {
            Connection con = dbCon.getConnection();
        }
        assertTrue("Should NOT be able to take mote connections than the default", false);
    }
}