package main.db_access_layer.managers.impl;

import main.db_access_layer.managers.DepositManager;
import main.model.Deposit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

public class DepositManagerImpl extends DbConnectorManagerImpl implements DepositManager {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");


    public DepositManagerImpl() {
        connectToDb();
    }

    @Override
    public void createNewDeposit(Deposit deposit) {
        try {
//            String smstStr = "INSERT INTO Clients VALUES(" + deposit.getDeposit_id() + ", " + deposit.getClient_id() + ", " + deposit.getBalance() + ", '" + deposit.getType().toString() + "', " + deposit.getEstimated_balance() + ", '" + deposit.getOpening_date() + "', '" + deposit.getClosing_date() + "')";
            sqlStrBldr = new StringBuilder("INSERT INTO Deposits ");
            sqlStrBldr.append("(client_id, balance, type, estimated_balance, opening_date, closing_date) ");
            sqlStrBldr.append("VALUES (");
            sqlStrBldr.append(deposit.getClient_id()).append(", ");
            sqlStrBldr.append(deposit.getBalance()).append(", '");
            sqlStrBldr.append(deposit.getType().toString()).append("', ");
            sqlStrBldr.append(deposit.getEstimated_balance()).append(", '");
            sqlStrBldr.append(df.format(deposit.getOpeningDate())).append("', '");
            sqlStrBldr.append(df.format(deposit.getClosingDate())).append("')");
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Deposit" + deposit.getDepositId() + " with " + deposit.getBalance() + " balance was created on DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Deposit " + deposit.getDepositId() + " already exists on DB. Deposit wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }
    }

    //TODO should be part of  an interface
    @Override
    public void drawDeposit(long deposit_id) {
    }

    @Override
    public Deposit findDeposit(long depositId) {
        sqlStrBldr = new StringBuilder("SELECT * FROM Deposits WHERE deposit_id=").append(depositId);
        try {
            ResultSet res = stmt.executeQuery(sqlStrBldr.toString());
        } catch (SQLException e) {
            String msg = "Could not find the requested deposit";
            LOGGER.log(Level.WARNING, msg);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Deposit findDeposit(Deposit deposit) {
        return findDeposit(deposit.getDepositId());
    }

    @Override
    public void updateDeposit(Deposit deposit) {
        sqlStrBldr = new StringBuilder("UPDATE Deposits SET ");
        sqlStrBldr.append("balance=").append(deposit.getBalance());
        sqlStrBldr.append(", type='").append(deposit.getType());
        sqlStrBldr.append("', estimate_balance=").append(deposit.getBalance());
        sqlStrBldr.append(", closing_date=").append(deposit.getClosingDate());
        try {
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Deposit " + deposit.getDepositId() + " was updated";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            String msg = "Deposit " + deposit.getDepositId() + " could not be updated";
            LOGGER.log(Level.WARNING, msg);
            e.printStackTrace();
        }
    }
}
