package main.db_access_layer.managers.impl;

import main.DepositType;
import main.db_access_layer.managers.DepositManager;
import main.exceptions.DbConnectorException;
import main.model.Deposit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;

public class DepositManagerImpl extends DbConnectorManagerImpl implements DepositManager {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");


    public DepositManagerImpl() throws DbConnectorException {
        connectToDb();
    }

    @Override
    public void createNewDeposit(Deposit deposit) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("INSERT INTO Deposits ");
        sqlStrBldr.append("(client_id, balance, type, estimated_balance, opening_date, closing_date) ");
        sqlStrBldr.append("VALUES (");
        sqlStrBldr.append(deposit.getClientId()).append(", ");
        sqlStrBldr.append(deposit.getBalance()).append(", '");
        sqlStrBldr.append(deposit.getType().toString()).append("', ");
        sqlStrBldr.append(deposit.getEstimatedBalance()).append(", '");
        sqlStrBldr.append(df.format(deposit.getOpeningDate())).append("', '");
        sqlStrBldr.append(df.format(deposit.getClosingDate())).append("')");
        executeUpdate(sqlStrBldr);
        String msg = "Deposit " + deposit.getDepositId() + " with " + deposit.getBalance() + " balance was created on DB";
        LOGGER.log(Level.INFO, msg);
    }

    @Override
    public void drawDeposit(long depositId) throws DbConnectorException {
        try {
            double depositAmount;
            long clientId = 0;
            Date today = Calendar.getInstance().getTime();
            Date closingDate;
            String msg, clientType;
            sqlStrBldr = new StringBuilder("SELECT d.balance, d.closing_date, d.client_id , c.type, a.account_id" +
                    "FROM Deposits d JOIN Clients c on d.client_id = c.client_id JOIN Accounts a on c.client_id = a.client_id" +
                    "WHERE deposit_id = ").append(depositId);
            ResultSet res = executeQuery(sqlStrBldr);
            if (res.next()) {
                depositAmount = res.getDouble(1);
                String closingDateString = res.getString(2);
                clientId = res.getLong(3);
                closingDate = new SimpleDateFormat("yy-MM-dd").parse(closingDateString);
                clientType = res.getString(4);
                long accountId = res.getLong(5);

                AccountManagerImpl accountDbConnector = new AccountManagerImpl();
                accountDbConnector.depositToAccount(accountId, depositAmount);
                if (today.compareTo(closingDate) < 0) {
                    //TODO - implement the whole interest
                    //TODO  implement with string builder
                    StringBuilder sb = new StringBuilder();

                    msg = "The closing date of deposit " + depositId + " is " + closingDateString +
                            ". Therefore a commission was charged.";
                    LOGGER.log(Level.INFO, msg);
                }

                msg = "deposit " + depositId + "was closed. " + depositAmount + " was added to the account";
                LOGGER.log(Level.INFO, msg);
            }
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        } catch (ParseException e) {
            throw new DbConnectorException(e);
        }
    }

    @Override
    public HashSet<Deposit> allClientsDeposits(long clientId) throws DbConnectorException {
        HashSet<Deposit> allDeposits = null;
        sqlStrBldr = new StringBuilder("SELECT * FROM Deposits WHERE client_id=").append(clientId);
        try {
            ResultSet res = executeQuery(sqlStrBldr);
            while (res.next()){
                allDeposits.add(buildDeposit(res));
            }
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        return allDeposits;
    }

    @Override
    public Deposit findDeposit(long depositId) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("SELECT * FROM Deposits WHERE deposit_id=").append(depositId);
        Deposit resDeposit = null;
        try {
            ResultSet res = executeQuery(sqlStrBldr.toString());
            if (res.next()) {
                resDeposit = buildDeposit(res);
            }
        } catch (SQLException e) {
            String msg = "Could not find the requested deposit";
            LOGGER.log(Level.WARNING, msg);
            throw new DbConnectorException(msg, e);
        }
        return resDeposit;
    }

    @Override
    public Deposit findDeposit(Deposit deposit) throws DbConnectorException {
        return findDeposit(deposit.getDepositId());
    }

    public void updateDeposit(Deposit deposit) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("UPDATE Deposits SET ");
        sqlStrBldr.append("balance=").append(deposit.getBalance());
        sqlStrBldr.append(", type='").append(deposit.getType());
        sqlStrBldr.append("', estimate_balance=").append(deposit.getBalance());
        sqlStrBldr.append(", closing_date=").append(deposit.getClosingDate());
        executeUpdate(sqlStrBldr);
        StringBuilder msg = new StringBuilder("Deposit ").append(deposit.getDepositId()).append(" was updated");
        LOGGER.log(Level.INFO, msg.toString());
    }

    @Override
    public HashSet<Deposit> allDeposits() throws DbConnectorException {
        HashSet<Deposit> allDeposits = new HashSet<Deposit>();
        sqlStrBldr = new StringBuilder("SELECT * FROM Deposits");
        try {
            ResultSet res = executeQuery(sqlStrBldr.toString());
            while (res.next()) {
                allDeposits.add(buildDeposit(res));
            }
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        return allDeposits;
    }

    @Override
    public HashSet<Deposit> allExpiredDeposits() throws DbConnectorException {
        HashSet<Deposit> allExpired = new HashSet<Deposit>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date now = Calendar.getInstance().getTime();
        String today = df.format(now);
        sqlStrBldr = new StringBuilder("SELECT * FROM Deposits WHERE closing_date < '").append(today).append("'");
        try {
            ResultSet res = stmt.executeQuery(sqlStrBldr.toString());
            while (res.next()) {
                allExpired.add(buildDeposit(res));
            }
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        return allExpired;
    }

    @Override
    public Deposit buildDeposit(ResultSet res) throws DbConnectorException {
        Deposit deposit = null;
        try {
            deposit = new Deposit(res.getLong(1), res.getLong(2), res.getDouble(3), DepositType.valueOf(res.getString(4)), res.getLong(5), df.parse(res.getString(6)), df.parse(res.getString(7)));
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        } catch (ParseException e) {
            throw new DbConnectorException(e);
        }
        return deposit;
    }

    @Override
    public void closeDeposit(Deposit deposit) throws DbConnectorException {
        closeDeposit(deposit.getDepositId());
    }

    @Override
    public void closeDeposit(long depositId) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("DELETE FROM Deposits WHERE deposit_id=").append(depositId);
        executeUpdate(sqlStrBldr.toString());
        LOGGER.log(Level.INFO, "Deposit " + depositId + " has been deleted");
    }
}
