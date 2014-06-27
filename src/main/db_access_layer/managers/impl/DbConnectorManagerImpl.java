package main.db_access_layer.managers.impl;

import config.ImportDbSettings;
import main.exceptions.DbConnectorException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DbConnectorManagerImpl {
    protected String sqlStr;
    protected StringBuilder sqlStrBldr = null;
    protected static Connection con;
    protected static Statement stmt;
    protected static Logger LOGGER = Logger.getLogger(DbConnectorManagerImpl.class.getName());

    protected Collection<Connection> connectionsPool;
    protected Collection<Connection> connectionsInUse;

    protected static Properties prop = ImportDbSettings.loadDbProperties();   // load DB properties from the config file

    private void initiateConnectionPool() throws DbConnectorException {
        int defaultConnectionsAmount = 50; //TODO - get this from somewhere
        String dbUrl = prop.getProperty("DB_ADDRESS") + "/" + prop.getProperty("DB_NAME");
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
        int poolExtensionAmount = 10;           //TODO - get this from somewhere
        int maximumAmountOfConections = 100;    //TODO - get this from somewhere as well
        if (connectionsPool.size() + poolExtensionAmount > maximumAmountOfConections) {
            String msg = "The amount of connections has exceeded the maximum amount of total connections" +  maximumAmountOfConections;
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

    public Connection getConnection() throws DbConnectorException {
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

    public void returnConnection(Connection con) {
        connectionsInUse.remove(con);
        connectionsPool.add(con);
    }

    public static void connectToDb() throws DbConnectorException {
        String dbUrl = prop.getProperty("DB_ADDRESS") + "/" + prop.getProperty("DB_NAME");
        try {
            con = DriverManager.getConnection(dbUrl);           // init to the DB
            System.out.println("Connected successfully to " + prop.getProperty("DB_NAME"));
            stmt = con.createStatement();
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
    }

    public static void disconnect() throws DbConnectorException {
        try {
            con.close();                                                    // always remember to close the connection at the end
            System.out.println("Connection closed");
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
    }

    public static void executeStatement(String statement, String logMessage) throws DbConnectorException {
        try {
            stmt.executeUpdate(statement);
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        LOGGER.log(Level.INFO, logMessage);
    }
}
