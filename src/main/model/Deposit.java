package main.model;

import main.DepositType;

import java.util.Date;

/**
 * Created by Ariel Voskov on 4/25/2014.
 */
public class Deposit {
    private long deposit_id;
    private long client_id;
    private double balance;
    private DepositType type;
    private long estimated_balance;
    private Date opening_date;
    private Date closing_date;

    public long getDeposit_id() {
        return deposit_id;
    }

    public void setDeposit_id(long deposit_id) {
        this.deposit_id = deposit_id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public DepositType getType() {
        return type;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    public long getEstimated_balance() {
        return estimated_balance;
    }

    public void setEstimated_balance(long estimated_balance) {
        this.estimated_balance = estimated_balance;
    }

    public Date getOpening_date() {
        return opening_date;    //TODO
    }

    public void setOpening_date(Date opening_date) {
        this.opening_date = opening_date;
    }

    public Date getClosing_date() {
        return closing_date;    //TODO
    }

    public void setClosing_date(Date closing_date) {
        this.closing_date = closing_date;
    }

    public Deposit() {
    }

    public Deposit(long deposit_id, long client_id, double balance, DepositType type, long estimated_balance, Date opening_date, Date closing_date) {
        this.deposit_id = deposit_id;
        this.client_id = client_id;
        this.balance = balance;
        this.type = type;
        this.estimated_balance = estimated_balance;
        this.opening_date = opening_date;
        this.closing_date = closing_date;
    }
}
