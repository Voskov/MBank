package test;

import init.DropDb;
import init.InitiateDB;
import main.db_access_layer.managers.impl.DbConnectorManagerImpl;
import main.exceptions.DbConnectorException;
import org.junit.After;
import org.junit.Before;

public class AbstractTest {

    @Before
    public void setUp() throws DbConnectorException {
        DropDb.dropAllTables();
        InitiateDB.createDb();
    }



    @After
    public void tearDown() throws DbConnectorException {
        DbConnectorManagerImpl temp = new DbConnectorManagerImpl();
        temp.getPool().drainConnectionPool();
        temp = null;
    }


}
