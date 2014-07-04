package test;

import main.db_access_layer.managers.impl.DbConnectorManagerImpl;
import main.exceptions.DbConnectorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DbConnectorManagerImplTest {
    DbConnectorManagerImpl dbCon;

    @Before
    public void setUp() throws Exception {
        dbCon = new DbConnectorManagerImpl();
    }

    @After
    public void tearDown() throws DbConnectorException {
//        dbCon.drainConnectionPool();
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
}