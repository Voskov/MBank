package classes;

public class Account {
    private long account_id;
    private long client_id;
    private double balance;
    private double credit_limit;
    private String comment;

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
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

    public double getCredit_limit() {
        return credit_limit;
    }

    public void setCredit_limit(double credit_limit) {
        this.credit_limit = credit_limit;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Account() {
    }

    public Account(long account_id, long client_id, double balance, double credit_limit, String comment) {
        this.account_id = account_id;
        this.client_id = client_id;
        this.balance = balance;
        this.credit_limit = credit_limit;
        this.comment = comment;
    }
}
