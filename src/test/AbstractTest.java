package test;

import init.DropDb;
import init.InitiateDB;
import main.db_access_layer.managers.impl.DbConnectorManagerImpl;
import org.junit.After;
import org.junit.Before;

public class AbstractTest {

    @Before
    public void before() {
        DropDb.dropAllTables();
        InitiateDB.createDb();
    }



    @After
    public void after() {
//        DropTables.dropAllTables();
        DbConnectorManagerImpl.disconnect();
    }


}
