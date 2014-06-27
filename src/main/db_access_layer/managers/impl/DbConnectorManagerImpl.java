package main.db_access_layer.managers.impl;

import config.ImportDbSettings;
import main.exceptions.DbConnectorException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DbConnectorManagerImpl   {
    protected String sqlStr;
    protected StringBuilder sqlStrBldr = null;
    protected static Connection con;
    protected static Statement stmt;
    protected static Logger LOGGER = Logger.getLogger(DbConnectorManagerImpl.class.getName());

    protected static Properties prop = ImportDbSettings.loadDbProperties();   // load DB properties from the config file

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
