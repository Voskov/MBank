package main.model;

import main.AccountType;

public class Client {
    private long clientId;
    private String client_name;
    private String password;
    private AccountType accountType;
    private String address;
    private String email;
    private String phone;
    private String comment;

    public Client(long clientId, String clientName, String password, AccountType accountType, String address, String email, String phone, String comment) {
        this.clientId = clientId;
        this.client_name = clientName;
        this.password = password;
        this.accountType = accountType;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
    }

    public Client(String client_name, String password, AccountType accountType, String address, String email, String phone, String comment) {
        this.client_name = client_name;
        this.password = password;
        this.accountType = accountType;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
    }

    public Client(String client_name, String password, String address, String email, String phone, String comment) {
        this.client_name = client_name;
        this.password = password;
        this.accountType = null;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
    }

    public Client(){

    }

    public void setClientId(long client_id) {
        this.clientId = client_id;
    }

    public long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return client_name;
    }

    public void setClientName(String client_name) {
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
                "clientId=" + clientId +
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

        if (clientId != client.clientId) return false;
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