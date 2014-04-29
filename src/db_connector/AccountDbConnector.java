package db_connector;

import classes.Account;
import config.ImportDbSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

public class AccountDbConnector extends DbConnector{
    private final static Logger LOGGER = Logger.getLogger(AccountDbConnector.class.getName());

    private static Properties prop = ImportDbSettings.loadDbProperties();   // load DB properties from the config file

    public AccountDbConnector(){
        connectToDb();
    }

    public void addAccount(Account acc) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO Accounts(" + acc.getAccount_id() + ", " + acc.getClient_id() + ", " + acc.getBalance() + ", " + acc.getCredit_limit() + ", '" + acc.getComment() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
