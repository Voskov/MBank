package main.model;

import main.DepositType;

import java.util.Date;

public class Deposit {
    private long depositId;
    private long clientId;
    private double balance;
    private DepositType type;
    private long estimatedBalance;
    private Date openingDate;
    private Date closingDate;

    public long getDepositId() {
        return depositId;
    }

    public void setDepositId(long deposit_id) {
        this.depositId = deposit_id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
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
        return estimatedBalance;
    }

    public void setEstimatedBalance(long estimatedBalance) {
        this.estimatedBalance = estimatedBalance;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Deposit() {
    }

    public Deposit(long depositId, long clientId, double balance, DepositType type, long estimatedBalance, Date openingDate, Date closingDate) {
        this.depositId = depositId;
        this.clientId = clientId;
        this.balance = balance;
        this.type = type;
        this.estimatedBalance = estimatedBalance;
        openingDate.setHours(0);
        openingDate.setMinutes(0);
        openingDate.setSeconds(0);
        openingDate.setMinutes(0);
        closingDate.setHours(0);
        closingDate.setMinutes(0);
        closingDate.setSeconds(0);
        closingDate.setMinutes(0);
        this.openingDate = openingDate;
        this.closingDate = closingDate;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deposit)) return false;

        Deposit deposit = (Deposit) o;

        if (Double.compare(deposit.balance, balance) != 0) return false;
        if (clientId != deposit.clientId) return false;
        if (depositId != deposit.depositId) return false;
        if (estimatedBalance != deposit.estimatedBalance) return false;
        if (closingDate != null ? !closingDate.equals(deposit.closingDate) : deposit.closingDate != null)   // Here - something's wrong here
            return false;
        if (openingDate != null ? !openingDate.equals(deposit.openingDate) : deposit.openingDate != null)
            return false;
        if (type != deposit.type) return false;
        return true;
    }
}
