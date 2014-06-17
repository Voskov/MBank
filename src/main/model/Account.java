package main.model;

public class Account {
    private long accountId;
    private long clientId;
    private double balance;
    private double creditLimit;
    private String comment;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId1) {
        this.accountId = accountId1;
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

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Account() {
    }

    public Account(long accountId, long clientId, double balance, double creditLimit, String comment) {
        this.accountId = accountId;
        this.clientId = clientId;
        this.balance = balance;
        this.creditLimit = creditLimit;
        this.comment = comment;
    }

    public Account(long clientId, double balance, double creditLimit, String comment) {
        this.clientId = clientId;
        this.balance = balance;
        this.creditLimit = creditLimit;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + accountId +
                ", client_id=" + clientId +
                ", balance=" + balance +
                ", credit_limit=" + creditLimit +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountId != account.accountId) return false;
        if (Double.compare(account.balance, balance) != 0) return false;
        if (clientId != account.clientId) return false;
        if (Double.compare(account.creditLimit, creditLimit) != 0) return false;
        if (comment != null ? !comment.equals(account.comment) : account.comment != null) return false;

        return true;
    }
}
