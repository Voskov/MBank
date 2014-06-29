package main.db_access_layer.managers;

import main.exceptions.DbConnectorException;

import java.sql.Connection;

public interface DbConnectorManager {
    Connection getConnection() throws DbConnectorException;

    void returnConnection(Connection con);
}
