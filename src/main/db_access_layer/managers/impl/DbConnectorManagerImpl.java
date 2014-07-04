package main.db_access_layer.managers.impl;

import config.ImportDbSettings;
import init.InitiateDB;
import main.db_access_layer.managers.ConnectionPool;
import main.db_access_layer.managers.DbConnectorManager;
import main.exceptions.DbConnectorException;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DbConnectorManagerImpl implements DbConnectorManager {
    protected String sqlStr;    // TODO - get rid of this
    protected StringBuilder sqlStrBldr = null;
    protected static Connection con;    // TODO - get rid of this
    protected static Statement stmt;    // TODO - get rid of this
    protected static Logger LOGGER = Logger.getLogger(DbConnectorManagerImpl.class.getName());

    ConnectionPool pool = new ConnectionPoolImpl();

//    public static Collection<Connection> connectionsPool;
//    public static Collection<Connection> connectionsInUse;

    protected static Properties prop = ImportDbSettings.loadDbProperties();   // load DB properties from the config file

    public DbConnectorManagerImpl() throws DbConnectorException {
//        initiateConnectionPool();
    }

//    public int getAmountOfConncetionsReady() {
//        return connectionsPool.size();
//    }

//    public int getAmountOfConncetionsInUse() {
//        return connectionsInUse.size();
//    }

//    public void initiateConnectionPool() throws DbConnectorException {
//        int defaultConnectionsAmount = Integer.parseInt(prop.getProperty("DEFAULT_CONNECTIONS_AMOUNT"));  //50
//        String dbUrl = prop.getProperty("DB_ADDRESS") + "/" + prop.getProperty("DB_NAME");
//        connectionsInUse = new HashSet<Connection>();
//        connectionsPool = new HashSet<Connection>();
//        for (int i = 0; i < defaultConnectionsAmount; i++) {
//            try {
//                connectionsPool.add(DriverManager.getConnection(dbUrl));
//            } catch (SQLException e) {
//                throw new DbConnectorException(e);
//            }
//        }
//    }

//    private void expandConnectionsPool() throws DbConnectorException {
//        int poolExtensionAmount = Integer.parseInt(prop.getProperty("CONNECTIONS_AMOUNT_INTERVAL"));  //10
//        int maximumAmountOfConections = Integer.parseInt(prop.getProperty("MAXIMUM_CONNECTIONS_AMOUNT"));  //100
//        if (connectionsPool.size() + poolExtensionAmount > maximumAmountOfConections) {
//            String msg = "The amount of connections has exceeded the maximum amount of total connections" + maximumAmountOfConections;
//            throw new DbConnectorException(msg);
//        }
//        String dbUrl = prop.getProperty("DB_ADDRESS") + "/" + prop.getProperty("DB_NAME");
//        for (int i = 0; i < poolExtensionAmount; i++) {
//            try {
//                connectionsPool.add(DriverManager.getConnection(dbUrl));
//            } catch (SQLException e) {
//                throw new DbConnectorException(e);
//            }
//        }
//    }

//    public void drainConnectionPool() throws DbConnectorException {
//        LOGGER.log(Level.INFO, "Closing all connections");
//        for (Connection con : connectionsPool) {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                String msg = "Could not close all connections";
//                throw new DbConnectorException(msg, e);
//            }
//        }
//        connectionsPool.removeAll(connectionsPool);
//        for (Connection con : connectionsInUse) {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                String msg = "Could not close all connections";
//                throw new DbConnectorException(msg, e);
//            }
//        }
//        connectionsInUse.removeAll(connectionsInUse);
//    }

//    @Override
//    public Connection getConnection() throws DbConnectorException {
//        if (!connectionsPool.isEmpty()) {
//            Connection con = connectionsPool.iterator().next();
//            connectionsInUse.add(con);
//            connectionsPool.remove(con);
//            return con;
//        } else {
//            expandConnectionsPool();
//        }
//        return null;
//    }
//
//    @Override
//    public void returnConnection(Connection con) {
//        connectionsInUse.remove(con);
//        connectionsPool.add(con);
//
//    }

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

    @Deprecated
    public static void disconnect() throws DbConnectorException {
        try {
            con.close();                                                    // always remember to close the connection at the end
            System.out.println("Connection closed");
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
    }

    @Deprecated
    public static void executeStatement(String sqlQuery, String logMessage) throws DbConnectorException {
        try {
            stmt.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        LOGGER.log(Level.INFO, logMessage);
    }

    public void executeUpdate(StringBuilder sqlStrBldr) throws DbConnectorException {
        executeUpdate(sqlStrBldr.toString());
    }

    public void executeUpdate(String sqlQuery) throws DbConnectorException {
        Connection connection = pool.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            pool.returnConnection(connection);
        } catch (SQLException e) {
            String msg = "Failed making an SQL query" + sqlQuery;
            pool.returnConnection(connection);
            throw new DbConnectorException(msg, e);
        }
    }

    public ResultSet executeQuery(StringBuilder sqlStrBldr) throws DbConnectorException {
        return executeQuery(sqlStrBldr.toString());
    }

    public ResultSet executeQuery(String sqlQuery) throws DbConnectorException {
        ResultSet res = null;
        Connection connection = pool.getConnection();
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery(sqlQuery);
            pool.returnConnection(connection);
        } catch (SQLException e) {
            String msg = "Failed making an SQL query - " + sqlQuery;
            pool.returnConnection(connection);
            throw new DbConnectorException(msg, e);
        }
        return res;
    }
}
