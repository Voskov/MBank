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
    public void setUp() throws DbConnectorException {
        dbCon = new DbConnectorManagerImpl();
    }

    @After
    public void tearDown() throws DbConnectorException {
//        dbCon.drainConnectionPool();
    }

}