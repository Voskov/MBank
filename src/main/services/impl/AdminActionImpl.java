package main.services.impl;

import main.AccountType;
import main.db_access_layer.managers.*;
import main.db_access_layer.managers.impl.*;
import main.model.Account;
import main.model.Activity;
import main.model.Client;
import main.model.Deposit;
import main.services.AdminAction;
import main.services.ClientAction;

import java.sql.SQLException;
import java.util.HashSet;

public class AdminActionImpl implements AdminAction {
    @Override
    public void addNewClient(Client client, double initialAmount) throws Exception {
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
        Client dbClient = cm.findClient(client.getClient_name());

        Account initialAccount = new Account(dbClient.getClient_id(), initialAmount, limit, "Initial account");
        AccountManager am = new AccountManagerImpl();
        am.createAccount(initialAccount);

    }

    @Override
    public void removeClient(long client_id) throws Exception {
        ClientManager cm = new ClientManagerImpl();
        Client client = cm.findClient(client_id);
        removeClient(client);
    }

    @Override
    public void removeClient(Client client) throws Exception {
        DepositManager dm = new DepositManagerImpl();
        HashSet<Deposit> allDeposits = dm.allClientsDeposits(client.getClient_id());
        if (allDeposits != null) {
            for (Deposit deposit : allDeposits) {
                ClientAction ca = new ClientActionImpl();
                ca.preOpenDeposit(deposit.getDepositId());  //this may be problematic because short deposits cannot be preopened
            }
        }
        AccountManager am = new AccountManagerImpl();
        HashSet<Account> allAccounts = am.allClientsAccounts(client.getClient_id());
        if (!allAccounts.isEmpty()) {
            for (Account account : allAccounts) {
                this.removeAccount(account);
            }
        }
        ClientManager cm = new ClientManagerImpl();
        cm.deleteClient(client);
    }

    @Override
    public void createNewAccount(Account account) {
        AccountManager accountManager = new AccountManagerImpl();
        accountManager.createAccount(account);
    }

    @Override
    public void removeAccount(Account account) {
        AccountManager accountManager = new AccountManagerImpl();

    }

    @Override
    public Client viewAllClientsDetails(long client_id) {
        ClientManager cm = new ClientManagerImpl();
        return cm.findClient(client_id);
    }

    @Override
    public Account viewAllAccountsDetails(long accountId) {
        AccountManager am = new AccountManagerImpl();
        return am.findAccount(accountId);

    }

    @Override
    public Deposit viewAllDepositsDetails(long depositId) {
        DepositManager dm = new DepositManagerImpl();
        return dm.findDeposit(depositId);
    }

    @Override
    public void ViewAllActivitiesDetails() {

    }

    @Override
    public Activity ViewAllActivitiesDetails(long activityId) throws SQLException {
        ActivityManager am = new ActivityManagerImpl();
        return am.findActivity(activityId);
    }

    @Override
    public void updateSystemProperty(String property, String value) {
        PropertyManager pm = new PropertyManagerImpl();
        pm.setProperty(property, value);
    }

    @Override
    public void updateSystemProperty(String property, double value) {
        updateSystemProperty(property, Double.toString(value));
    }

    @Override
    public void updateSystemProperty(AccountType type, String property, String value) {
        property += type.toString().toLowerCase();
        updateSystemProperty(property, value);
    }

    @Override
    public void updateSystemProperty(AccountType type, String property, double value) {
        property += type.toString().toLowerCase();
        updateSystemProperty(property, Double.toString(value));
    }

    @Override
    public void updateClientDetails(Client updatedClient) {

    }

    @Override
    public void viewClientDetails() {

    }

    @Override
    public void viewAccountDetails() {

    }

    @Override
    public void viewClientDeposits() {

    }

    @Override
    public void viewClientActivities() {

    }

    @Override
    public void viewSystemProperty() {

    }
}
