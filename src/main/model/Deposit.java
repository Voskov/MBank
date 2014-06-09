package main.model;

import main.DepositType;

import java.util.Calendar;
import java.util.Date;

public class Deposit {
    private long deposit_id;
    private long client_id;
    private double balance;
    private DepositType type;
    private long estimated_balance;
    private Date opening_date;
    private Date closing_date;

    public long getDepositId() {
        return deposit_id;
    }

    public void setDeposit_id(long deposit_id) {
        this.deposit_id = deposit_id;
    }

    public long getClientId() {
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

    public long getEstimatedBalance() {
        return estimated_balance;
    }

    public void setEstimated_balance(long estimated_balance) {
        this.estimated_balance = estimated_balance;
    }

    public Date getOpeningDate() {
        return opening_date;    //TODO
    }

    public void setOpening_date(Date opening_date) {
        this.opening_date = opening_date;
    }

    public Date getClosingDate() {
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
        opening_date.setHours(0);
        opening_date.setMinutes(0);
        opening_date.setSeconds(0);
        opening_date.setMinutes(0);
        closing_date.setHours(0);
        closing_date.setMinutes(0);
        closing_date.setSeconds(0);
        closing_date.setMinutes(0);
        this.opening_date = opening_date;
        this.closing_date = closing_date;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deposit)) return false;

        Deposit deposit = (Deposit) o;

        if (Double.compare(deposit.balance, balance) != 0) return false;
        if (client_id != deposit.client_id) return false;
        if (deposit_id != deposit.deposit_id) return false;
        if (estimated_balance != deposit.estimated_balance) return false;
        if (closing_date != null ? !closing_date.equals(deposit.closing_date) : deposit.closing_date != null)   // Here - something's wrong here
            return false;
        if (opening_date != null ? !opening_date.equals(deposit.opening_date) : deposit.opening_date != null)
            return false;
        if (type != deposit.type) return false;
        return true;
    }
}
