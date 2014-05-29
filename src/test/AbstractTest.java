package test;

import connect.DropTables;
import connect.InitiateDB;

public class AbstractTest {
    public void dropTables(){
        DropTables.dropAllTables();
    }

    public void createTables(){
        InitiateDB.createDb();
    }
}
