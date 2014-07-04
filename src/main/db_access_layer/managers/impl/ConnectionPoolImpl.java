package main.db_access_layer.managers.impl;

import config.ImportDbSettings;
import main.db_access_layer.managers.ConnectionPool;
import main.exceptions.DbConnectorException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPoolImpl implements ConnectionPool{

    private static Collection<Connection> connectionsPool;
    private static Collection<Connection> connectionsInUse;

    private static Properties prop = ImportDbSettings.loadDbProperties();   // load DB properties from the config file

    protected static Logger LOGGER = Logger.getLogger(DbConnectorManagerImpl.class.getName());



    public void initiateConnectionPool() throws DbConnectorException {
        int defaultConnectionsAmount = Integer.parseInt(prop.getProperty("DEFAULT_CONNECTIONS_AMOUNT"));  //50
        String dbUrl = prop.getProperty("DB_ADDRESS") + "/" + prop.getProperty("DB_NAME");
        connectionsInUse = new HashSet<Connection>();
        connectionsPool = new HashSet<Connection>();
        for (int i = 0; i < defaultConnectionsAmount; i++) {
            try {
                connectionsPool.add(DriverManager.getConnection(dbUrl));
            } catch (SQLException e) {
                throw new DbConnectorException(e);
            }
        }
    }

    private void expandConnectionsPool() throws DbConnectorException {
        int poolExtensionAmount = Integer.parseInt(prop.getProperty("CONNECTIONS_AMOUNT_INTERVAL"));  //10
        int maximumAmountOfConections = Integer.parseInt(prop.getProperty("MAXIMUM_CONNECTIONS_AMOUNT"));  //100
        if (getAmountOfConncetionsInUse() + poolExtensionAmount > maximumAmountOfConections) {
            String msg = "The amount of connections has exceeded the maximum amount of total connections" + maximumAmountOfConections;
            throw new DbConnectorException(msg);
        }
        String dbUrl = prop.getProperty("DB_ADDRESS") + "/" + prop.getProperty("DB_NAME");
        for (int i = 0; i < poolExtensionAmount; i++) {
            try {
                connectionsPool.add(DriverManager.getConnection(dbUrl));
            } catch (SQLException e) {
                throw new DbConnectorException(e);
            }
        }
    }

    public void drainConnectionPool() throws DbConnectorException {
        LOGGER.log(Level.INFO, "Closing all connections");
        for (Connection con : connectionsPool) {
            try {
                con.close();
            } catch (SQLException e) {
                String msg = "Could not close all connections";
                throw new DbConnectorException(msg, e);
            }
        }
        connectionsPool.removeAll(connectionsPool);
        for (Connection con : connectionsInUse) {
            try {
                con.close();
            } catch (SQLException e) {
                String msg = "Could not close all connections";
                throw new DbConnectorException(msg, e);
            }
        }
        connectionsInUse.removeAll(connectionsInUse);
    }

    @Override
    public Connection getConnection() throws DbConnectorException {
        if (connectionsPool == null) {
            initiateConnectionPool();
        }
        if (!connectionsPool.isEmpty()) {
            Connection con = connectionsPool.iterator().next();
            connectionsInUse.add(con);
            connectionsPool.remove(con);
            return con;
        } else {
            expandConnectionsPool();
        }
        return null;
    }

    @Override
    public void returnConnection(Connection con) {
        connectionsInUse.remove(con);
        connectionsPool.add(con);

    }

    @Override
    public int getAmountOfConncetionsReady() {
        return connectionsPool.size();
    }

    @Override
    public int getAmountOfConncetionsInUse() {
        return connectionsInUse.size();
    }
}
