package db_connector;

import Managers.DepositManager;
import classes.Deposit;
import classes.DepositType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;

public class DepositDbConnector extends DbConnector implements DepositManager{

    public DepositDbConnector() {
        connectToDb();
    }

    public void createDeposit(Deposit dep) {
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

    @Override
    public void createNewDeposit(long deposit_id, long client_id, double balance, DepositType type, long estimated_balance, String opening_date, String closing_date) {
        try {
            String smstStr = "INSERT INTO Clients VALUES(" + deposit_id + ", " + client_id + ", " + balance + ", '" + type + "', " + estimated_balance + ", '" + opening_date + "', '" + closing_date + "')";
            stmt.executeUpdate(smstStr);
            String msg = "Deposit" + deposit_id + " with " + balance + " balance was created on DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Deposit " + deposit_id + " already exists on DB. Deposit wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeDeposit(long deposit_id) {
        try{
            double balance;
            long client_id = 0;
            String smstStr = "SELECT * FROM Deposits WHERE deposit_id = \"" + deposit_id + "\"";
            ResultSet res = stmt.executeQuery(smstStr);
            AccountDbConnector AC = new AccountDbConnector() ;
            if (res.next()){
                balance = res.getDouble(3);
                client_id = res.getLong(2);
                AC.depositToAccount();
                String msg = "deposit " + deposit_id + "was closed. " + balance + " was added to ";
                LOGGER.log(Level.INFO, msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double preopenDeposit() {
        return 0;
    }
}
