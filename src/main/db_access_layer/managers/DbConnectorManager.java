package main.db_access_layer.managers;

import main.exceptions.DbConnectorException;

import java.sql.Connection;
import java.util.Collection;

public interface DbConnectorManager {
    public static Collection<Connection> connectionsPool = null;
    public static Collection<Connection> connectionsInUse = null;

    Connection getConnection() throws DbConnectorException;

    void returnConnection(Connection con);
}
