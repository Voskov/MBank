package test;

import init.InitiateDB;
import main.db_access_layer.managers.ConnectionPool;
import main.db_access_layer.managers.impl.ConnectionPoolImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ConnectionPoolTest {
    ConnectionPool pool = null;

    @Before
    public void setUp() throws Exception {
        InitiateDB.restartDb();
        pool = new ConnectionPoolImpl();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetConnection() throws Exception {
        Connection con = pool.getConnection();
        assertEquals("Should be 1", 1, pool.getAmountOfConncetionsInUse());
        assertEquals();
    }

    @Test
    public void testReturnConnection() throws Exception {

    }

    @Test
    public void testGetAmountOfConncetionsReady() throws Exception {

    }

    @Test
    public void testGetAmountOfConncetionsInUse() throws Exception {

    }
}