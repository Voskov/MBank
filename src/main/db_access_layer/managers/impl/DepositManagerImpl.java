package main.db_access_layer.managers.impl;

import main.DepositType;
import main.db_access_layer.managers.DepositManager;
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


    public DepositManagerImpl() {
        connectToDb();
    }

    @Override
    public void createNewDeposit(Deposit deposit) {
        try {
            sqlStrBldr = new StringBuilder("INSERT INTO Deposits ");
            sqlStrBldr.append("(client_id, balance, type, estimated_balance, opening_date, closing_date) ");
            sqlStrBldr.append("VALUES (");
            sqlStrBldr.append(deposit.getClientId()).append(", ");
            sqlStrBldr.append(deposit.getBalance()).append(", '");
            sqlStrBldr.append(deposit.getType().toString()).append("', ");
            sqlStrBldr.append(deposit.getEstimatedBalance()).append(", '");
            sqlStrBldr.append(df.format(deposit.getOpeningDate())).append("', '");
            sqlStrBldr.append(df.format(deposit.getClosingDate())).append("')");
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Deposit " + deposit.getDepositId() + " with " + deposit.getBalance() + " balance was created on DB";
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
        try {
            double deposit_amount;
            long client_id = 0;
            Date today = Calendar.getInstance().getTime();
            Date closing_date;
            String msg, client_type;
            String sqlStr = "SELECT d.balance, d.closing_date, d.client_id , c.type, a.account_id" +
                    "FROM Deposits d JOIN Clients c on d.client_id = c.client_id JOIN Accounts a on c.client_id = a.client_id" +
                    "WHERE deposit_id = " + deposit_id;
            ResultSet res = stmt.executeQuery(sqlStr);
            if (res.next()) {
                deposit_amount = res.getDouble(1);
                String closing_date_string = res.getString(2);
                client_id = res.getLong(3);
                closing_date = new SimpleDateFormat("yy-MM-dd").parse(closing_date_string);
                client_type = res.getString(4);
                long account_id = res.getLong(5);

                AccountManagerImpl accountDbConnector = new AccountManagerImpl();
                accountDbConnector.depositToAccount(account_id, deposit_amount);
                if (today.compareTo(closing_date) < 0) {
                    //TODO - implement the whole interest
                    //TODO  implement with string builder
                    StringBuilder sb = new StringBuilder();

                    msg = "The closing date of deposit " + deposit_id + " is " + closing_date_string +
                            ". Therefore a commission was charged.";
                    LOGGER.log(Level.INFO, msg);
                }


                msg = "deposit " + deposit_id + "was closed. " + deposit_amount + " was added to the account";
                LOGGER.log(Level.INFO, msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashSet<Deposit> allClientsDeposits(long client_id) {
        HashSet<Deposit> allDeposits = null;
        sqlStrBldr = new StringBuilder("SELECT * FROM Deposits WHERE client_id=").append(client_id);
        try {
            ResultSet res = stmt.executeQuery(sqlStrBldr.toString());
            while (res.next()){
                allDeposits.add(buildDeposit(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDeposits;
    }

    @Override
    public Deposit findDeposit(long depositId) {
        sqlStrBldr = new StringBuilder("SELECT * FROM Deposits WHERE deposit_id=").append(depositId);
        Deposit res_deposit = null;
        try {
            ResultSet res = stmt.executeQuery(sqlStrBldr.toString());
            if (res.next()) {
                res_deposit = buildDeposit(res);
            }
        } catch (SQLException e) {
            String msg = "Could not find the requested deposit";
            LOGGER.log(Level.WARNING, msg);
            e.printStackTrace();
        }
        return res_deposit;
    }

    @Override
    public Deposit findDeposit(Deposit deposit) {
        return findDeposit(deposit.getDepositId());
    }

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

    @Override
    public HashSet<Deposit> allDeposits() {
        HashSet<Deposit> allDeposits = new HashSet<Deposit>();
        sqlStrBldr = new StringBuilder("SELECT * FROM Deposits");
        try {
            ResultSet res = stmt.executeQuery(sqlStrBldr.toString());
            while (res.next()) {
                allDeposits.add(buildDeposit(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDeposits;
    }

    @Override
    public HashSet<Deposit> allExpiredDeposits() {
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
            e.printStackTrace();
        }
        return allExpired;
    }

    @Override
    public Deposit buildDeposit(ResultSet res) {
        Deposit deposit = null;
        try {
            deposit = new Deposit(res.getLong(1), res.getLong(2), res.getDouble(3), DepositType.valueOf(res.getString(4)), res.getLong(5), df.parse(res.getString(6)), df.parse(res.getString(7)));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return deposit;
    }

    @Override
    public void closeDeposit(Deposit deposit) {
        closeDeposit(deposit.getDepositId());
    }

    @Override
    public void closeDeposit(long deposit_id) {
        sqlStrBldr = new StringBuilder("DELETE FROM Deposits WHERE deposit_id=").append(deposit_id);
        try {
            stmt.executeUpdate(sqlStrBldr.toString());
            LOGGER.log(Level.INFO, "Deposit " + deposit_id + " has been deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
