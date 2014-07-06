package main.db_access_layer.managers;

import main.exceptions.DbConnectorException;

import java.sql.Connection;

public interface ConnectionPool {
    void drainConnectionPool() throws DbConnectorException;

    public Connection getConnection() throws DbConnectorException;

    public void returnConnection(Connection connection);

    int getAmountOfConncetionsReady();

    int getAmountOfConncetionsInUse();

    boolean isEmpty();
}