package test;

import init.DropTables;
import init.InitiateDB;

public class AbstractTest {
    public void dropTables(){
        DropTables.dropAllTables();
    }

    public void createTables(){
        InitiateDB.createDb();
    }
}
