package db_connector;

import Managers.DepositManager;
import classes.Deposit;
import classes.DepositType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

public class DepositDbConnector extends DbConnector implements DepositManager {

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

    public void createNewDeposit(Deposit deposit) {
        try {
            String smstStr = "INSERT INTO Clients VALUES(" + deposit.getDeposit_id() + ", " + deposit.getClient_id() + ", " + deposit.getBalance() + ", '" + deposit.getType().toString() + "', " + deposit.getEstimated_balance() + ", '" + deposit.getOpening_date() + "', '" + deposit.getClosing_date() + "')";
            stmt.executeUpdate(smstStr);
            String msg = "Deposit" + deposit.getDeposit_id() + " with " + deposit.getBalance() + " balance was created on DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Deposit " + deposit.getDeposit_id() + " already exists on DB. Deposit wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeDeposit(long deposit_id) {
//        try {
//            double balance;
//            long client_id = 0;
//            String smstStr = "SELECT * FROM Deposits WHERE deposit_id = \"" + deposit_id + "\"";
//            ResultSet res = stmt.executeQuery(smstStr);
//            AccountDbConnector AC = new AccountDbConnector();
//            if (res.next()) {
//                balance = res.getDouble(3);
//                client_id = res.getLong(2);
//                AC.depositToAccount();
//                String msg = "deposit " + deposit_id + "was closed. " + balance + " was added to ";
//                LOGGER.log(Level.INFO, msg);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    //methode closeDeposit
    public void closeDeposit(Deposit deposit) {
        try {
            double balance;
            long client_id = 0;
            Date today = Calendar.getInstance().getTime();
            Date closing_date;
            String msg;
            String smstStr = "SELECT balance, closing_date FROM Deposits WHERE deposit_id = \"" + deposit.getDeposit_id() + "\"";
            ResultSet res = stmt.executeQuery(smstStr);
            if (res.next()) {
                balance = res.getDouble(1);
                String closing_date_string = res.getString(2);
                closing_date = new SimpleDateFormat("yy-MM-dd").parse(closing_date_string);
                if (today.compareTo(closing_date) < 0){
                    msg = "The closing date of deposit " + deposit.getDeposit_id() + " is " + closing_date_string +
                            ". Therefore a commission was charged.";
                    LOGGER.log(Level.INFO, msg);
                }
                msg = "deposit " + deposit.getDeposit_id() + "was closed. " + balance + " was added to the account";
                LOGGER.log(Level.INFO, msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public double preopenDeposit() {
        return 0;
    }
}
