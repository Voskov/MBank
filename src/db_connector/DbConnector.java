package db_connector;

import config.ImportDbSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DbConnector {
    private static Properties prop = ImportDbSettings.loadDbProperties();   // load DB propertirs from the config file
    public static void main(String[] args) {
        String db_url = prop.getProperty("DB_ADDRESS") + "/" + prop.getProperty("DB_NAME");
        try{
            Connection con = DriverManager.getConnection(db_url);           // connect to the DB
            System.out.println("Connected successfully to " + prop.getProperty("DB_NAME"));

            con.close();                                                    // always remember to close the connection at the end
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
