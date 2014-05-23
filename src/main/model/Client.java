package main.model;

import main.AccountType;

public class Client {
    private long client_id;
    private String client_name;
    private String password;
    private AccountType accountType;
    private String address;
    private String email;
    private String phone;
    private String comment;

    public Client(long client_id, String client_name, String password, String accountType, String address, String email, String phone, String comment) {
        this.client_id = client_id;
        this.client_name = client_name;
        this.password = password;
        this.accountType = AccountType.GOLD.valueOf(accountType);
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getClient_id() {
        return client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}