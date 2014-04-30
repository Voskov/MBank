package db_connector;

import classes.Account;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class AccountDbConnector extends DbConnector{
    public AccountDbConnector(){
        connectToDb();
    }

    public void addAccount(Account acc) {
        try {
            Statement stmt = con.createStatement();
            String sqlStatement = "INSERT INTO Accounts VALUES(" + acc.getAccount_id() + ", " + acc.getClient_id() + ", " + acc.getBalance() + ", " + acc.getCredit_limit() + ", '" + acc.getComment() + "')";
            stmt.executeUpdate(sqlStatement);
            String msg = "Account " + acc.getAccount_id() + " was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
