package main.services.impl;

import main.AccountType;
import main.db_access_layer.managers.*;
import main.db_access_layer.managers.impl.*;
import main.exceptions.ClientException;
import main.exceptions.DbConnectorException;
import main.model.Account;
import main.model.Activity;
import main.model.Client;
import main.model.Deposit;
import main.services.AdminAction;
import main.services.ClientAction;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;

public class AdminActionImpl implements AdminAction {
    @Override
    public void addNewClient(Client client, double initialAmount) throws DbConnectorException {
        PropertyManager pm = new PropertyManagerImpl();
        double regular_limit = pm.getProperty("regular_deposit_rate");
        double gold_limit = pm.getProperty("gold_deposit_rate");
        double platinum_limit = pm.getProperty("platinum_deposit_rate");
        double limit = 0;
        if (initialAmount > regular_limit) {
            client.setAccountType(AccountType.REGULAR);
            limit = regular_limit;
        } else if (initialAmount > gold_limit) {
            client.setAccountType(AccountType.GOLD);
            limit = gold_limit;
        } else if (initialAmount > platinum_limit) {
            client.setAccountType(AccountType.PLATINUM);
            limit = platinum_limit;
        }

        ClientManager cm = new ClientManagerImpl();
        cm.createClient(client);
        Client dbClient = cm.findClient(client.getClientName());

        Account initialAccount = new Account(dbClient.getClientId(), initialAmount, limit, "Initial account");
        AccountManager am = new AccountManagerImpl();
        am.createAccount(initialAccount);
        Activity activity = new Activity(client.getClientId(), initialAmount, new Date(), 0, "Account created");
        ActivityManager acm = new ActivityManagerImpl();
        acm.addActivity(activity);
    }

    @Override
    public void removeClient(long client_id) throws DbConnectorException, ClientException {
        ClientManager cm = new ClientManagerImpl();
        Client client = cm.findClient(client_id);
        removeClient(client);
    }

    @Override
    public void removeClient(Client client) throws DbConnectorException, ClientException {
        DepositManager dm = new DepositManagerImpl();
        HashSet<Deposit> allDeposits = dm.allClientsDeposits(client.getClientId());
        if (allDeposits != null && !allDeposits.isEmpty()) {
            for (Deposit deposit : allDeposits) {
                ClientAction ca = new ClientActionImpl();
                ca.preOpenDeposit(deposit.getDepositId());  //this may be problematic because short deposits cannot be preopened
            }
        }
        AccountManager am = new AccountManagerImpl();
        HashSet<Account> allAccounts = am.allClientsAccounts(client.getClientId());
        if (allAccounts != null && !allAccounts.isEmpty()) {
            for (Account account : allAccounts) {
                this.removeAccount(account);
            }
        }
        ClientManager cm = new ClientManagerImpl();
        cm.deleteClient(client);
    }

    @Override
    public void createNewAccount(Account account) throws DbConnectorException {
        AccountManager accountManager = new AccountManagerImpl();
        accountManager.createAccount(account);
    }

    @Override
    public double removeAccount(Account account) throws DbConnectorException {
        AccountManager am = new AccountManagerImpl();
        Account dbAccount = am.findAccount(account);
        double balance = dbAccount.getBalance();
        am.deleteAccount(account);
        return balance;
    }

    @Override
    public Client viewAllClientsDetails(long client_id) throws DbConnectorException {
        ClientManager cm = new ClientManagerImpl();
        return cm.findClient(client_id);
    }

    @Override
    public Account viewAllAccountsDetails(long accountId) throws DbConnectorException {
        AccountManager am = new AccountManagerImpl();
        return am.findAccount(accountId);

    }

    @Override
    public Deposit viewAllDepositsDetails(long depositId) throws DbConnectorException {
        DepositManager dm = new DepositManagerImpl();
        return dm.findDeposit(depositId);
    }

    @Override
    public void ViewAllActivitiesDetails() {

    }

    @Override
    public Activity ViewAllActivitiesDetails(long activityId) throws SQLException, DbConnectorException {
        ActivityManager am = new ActivityManagerImpl();
        return am.findActivity(activityId);
    }

    @Override
    public void updateClientDetails(Client updatedClient) throws DbConnectorException {
        ClientManager cm = new ClientManagerImpl();
        Client dbClient = cm.findClient(updatedClient);
        if (updatedClient.getClientName() != null) dbClient.setClientName(updatedClient.getClientName());
        if (updatedClient.getPassword() != null) dbClient.setPassword(updatedClient.getPassword());
        if (updatedClient.getAccountType() != null) dbClient.setAccountType(updatedClient.getAccountType());
        if (updatedClient.getAddress() != null) dbClient.setAddress(updatedClient.getAddress());
        if (updatedClient.getEmail() != null) dbClient.setEmail(updatedClient.getEmail());
        if (updatedClient.getPhone() != null) dbClient.setPhone(updatedClient.getPhone());
        if (updatedClient.getComment() != null) dbClient.setComment(updatedClient.getComment());
        cm.updateClient(dbClient);
    }

    @Override
    public Client viewClientDetails(Client client) throws DbConnectorException {
        return viewClientDetails(client.getClientId());
    }

    @Override
    public Client viewClientDetails(long clientId) throws DbConnectorException {
        ClientManager cm = new ClientManagerImpl();
        Client dbClient = cm.findClient(clientId);
        return dbClient;
    }

    @Override
    public Account viewAccountDetails(Account account) throws DbConnectorException {
        return viewAccountDetails(account.getAccountId());
    }

    @Override
    public Account viewAccountDetails(long accountId) throws DbConnectorException {
        AccountManager am = new AccountManagerImpl();
        Account dbAccount = am.findAccount(accountId);
        return dbAccount;
    }

    @Override
    public HashSet<Deposit> viewClientDeposits(Client client) throws DbConnectorException {
        DepositManager dm = new DepositManagerImpl();
        HashSet<Deposit> allDeposits = dm.allClientsDeposits(client.getClientId());
        return allDeposits;
    }

    @Override
    public void updateSystemProperty(String property, String value) throws DbConnectorException {
        PropertyManager pm = new PropertyManagerImpl();
        pm.setProperty(property, value);
    }

    @Override
    public void updateSystemProperty(String property, double value) throws DbConnectorException {
        updateSystemProperty(property, Double.toString(value));
    }

    @Override
    public void updateSystemProperty(AccountType type, String property, String value) throws DbConnectorException {
        property += type.toString().toLowerCase();
        updateSystemProperty(property, value);
    }

    @Override
    public void updateSystemProperty(AccountType type, String property, double value) throws DbConnectorException {
        property += type.toString().toLowerCase();
        updateSystemProperty(property, Double.toString(value));
    }

    @Override
    public double viewSystemProperty(String property) throws DbConnectorException {
        PropertyManager pm = new PropertyManagerImpl();
        return pm.getProperty(property);
    }

    @Override
    public double viewSystemProperty(AccountType type, String property) throws DbConnectorException {
        PropertyManager pm = new PropertyManagerImpl();
        return pm.getProperty(type, property);
    }
}
