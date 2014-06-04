package test;

import init.DropDb;
import init.InitiateDB;
import main.db_access_layer.managers.impl.DbConnectorManagerImpl;
import org.junit.After;
import org.junit.Before;

public class AbstractTest {

    @Before
    public void setUp() {
        DropDb.dropAllTables();
        InitiateDB.createDb();
    }



    @After
    public void tearDown() {
//        DropTables.dropAllTables();
        DbConnectorManagerImpl.disconnect();
    }


}
