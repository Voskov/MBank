package db_connector;

import classes.Deposit;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.logging.Level;

/**
 * Created by Ariel Voskov on 4/30/2014.
 */
public class DepositDbConnector extends DbConnector {

    public DepositDbConnector() {
        connectToDb();
    }

    public void addDeposit(Deposit dep) {
        try {

            String sqlStatement = "INSERT INTO Deposits VALUES(" + dep.getDeposit_id() + ", " + dep.getClient_id() + ", " + dep.getBalance() + ", '" + dep.getType() + "', " + dep.getEstimated_balance() + ", '" + dep.getOpening_date() + "', '" + dep.getClosing_date() + "')";
            stmt.executeUpdate(sqlStatement);
            String msg = "Deposit " + dep.getDeposit_id() + " was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Deposit " + dep.getDeposit_id() + "already exists on DB. Deposit wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClient(long client_id, String client_name, String password, String type, String address, String email, String phone, String comment) {
        try {

            String smstStr = "INSERT INTO Clients VALUES(" + client_id + ", '" + client_name + "', '" + password + "', '" + type + "', '" + address + "', '" + email + "', '" + phone + "', '" + comment + "')";
            stmt.executeUpdate(smstStr);
            String msg = "Client " + client_name + " was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Client " + client_id + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }

    }
}
