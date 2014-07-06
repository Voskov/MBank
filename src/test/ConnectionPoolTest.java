package test;

import init.InitiateDB;
import main.db_access_layer.managers.ConnectionPool;
import main.db_access_layer.managers.impl.ConnectionPoolImpl;
import main.exceptions.DbConnectorException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ConnectionPoolTest {
    ConnectionPool pool = null;

    @Before
    public void setUp()  {
        InitiateDB.restartDb();
        pool = new ConnectionPoolImpl();

    }

    @After
    public void tearDown() throws DbConnectorException {
        pool.drainConnectionPool();
        pool = null;
    }

    @Test
    public void testGetConnection() throws DbConnectorException {
        Connection con = pool.getConnection();
        assertEquals("Should be 1", 1, pool.getAmountOfConncetionsInUse());
        assertEquals("Should be 49", 49, pool.getAmountOfConncetionsReady());
    }

    @Test
    public void testReturnConnection() throws DbConnectorException {
        Connection con = pool.getConnection();
        pool.returnConnection(con);
        assertEquals("Should be 0", 0, pool.getAmountOfConncetionsInUse());
        assertEquals("Should be 50", 50, pool.getAmountOfConncetionsReady());
    }

    @Test
    public void testExtendPool() throws DbConnectorException {
        for (int i = 0; i <= 55; i++) {
            Connection con = pool.getConnection();
        }
        assertTrue("Should be able to create more than 50 connections", true);
    }

    @Test(expected = DbConnectorException.class)
    public void textTooManyConnections() throws DbConnectorException {
        for (int i = 0; i < 200; i++) {
            Connection con = pool.getConnection();
        }
        assertFalse("Shouldn't be able to make this many connection and get here", true);
    }
}