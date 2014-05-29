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

    public Client(long client_id, String client_name, String password, AccountType accountType, String address, String email, String phone, String comment) {
        this.client_id = client_id;
        this.client_name = client_name;
        this.password = password;
        this.accountType = accountType;
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

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + client_id +
                ", client_name='" + client_name + '\'' +
                ", password='" + password + '\'' +
                ", accountType=" + accountType +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (client_id != client.client_id) return false;
        if (accountType != client.accountType) return false;
        if (address != null ? !address.equals(client.address) : client.address != null) return false;
        if (client_name != null ? !client_name.equals(client.client_name) : client.client_name != null) return false;
        if (comment != null ? !comment.equals(client.comment) : client.comment != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (password != null ? !password.equals(client.password) : client.password != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;

        return true;
    }
}