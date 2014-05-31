package main.db_access_layer.managers.impl;

import config.ImportDbSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DbConnectorManagerImpl {
    protected String sqlStr;
    protected StringBuilder sqlStrBldr = new StringBuilder();
    protected static Connection con;
    protected static Statement stmt;
    protected static Logger LOGGER = Logger.getLogger(DbConnectorManagerImpl.class.getName());

    protected static Properties prop = ImportDbSettings.loadDbProperties();   // load DB properties from the config file

    public static void connectToDb() {
        String db_url = prop.getProperty("DB_ADDRESS") + "/" + prop.getProperty("DB_NAME");
        try {
            con = DriverManager.getConnection(db_url);           // init to the DB
            System.out.println("Connected successfully to " + prop.getProperty("DB_NAME"));
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            con.close();                                                    // always remember to close the connection at the end
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeStatement(String statement, String logMessage) {
        try {
            stmt.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, logMessage);
    }
}
