package main.managers.impl;

import main.managers.DepositManager;
import main.model.Deposit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

public class DepositManagerImpl extends DbConnector implements DepositManager {

    public DepositManagerImpl() {
        connectToDb();
    }



    @Override
    public long createNewDeposit(Deposit deposit) {
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
        return 0L;
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
                    StringBuilder sb  = new StringBuilder();

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

}
