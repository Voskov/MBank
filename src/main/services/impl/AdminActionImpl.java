package main.services.impl;

import main.AccountType;
import main.db_access_layer.managers.AccountManager;
import main.db_access_layer.managers.ClientManager;
import main.db_access_layer.managers.PropertyManager;
import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.db_access_layer.managers.impl.PropertyManagerImpl;
import main.model.Account;
import main.model.Client;
import main.services.AdminAction;

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
        } else if (initialAmount > gold_limit){
            client.setAccountType(AccountType.GOLD);
            limit = gold_limit;
        } else if (initialAmount > platinum_limit){
            client.setAccountType(AccountType.PLATINUM);
            limit = platinum_limit;
        }

        ClientManager cm = new ClientManagerImpl();
        cm.createClient(client);
        Client dbClient = cm.findClient(client.getClient_name());

        Account initialAccount = new Account(dbClient.getClient_id(), initialAmount, limit, "Initial account");
        AccountManager am = new AccountManagerImpl() ;
        am.createAccount(initialAccount);

    }

    @Override
    public void removeClient(long client_id) {

    }

    @Override
    public void removeClient(Client client) {

    }

    @Override
    public void createNewAccount(Account account) {
        AccountManager accountManager = new AccountManagerImpl();
        accountManager.createAccount(account);
    }

    @Override
    public void removeAccount(Account account) {
        AccountManager accountManager = new AccountManagerImpl();
        accountManager.createAccount(account);
    }

    @Override
    public void viewAllClientsDetails() {

    }

    @Override
    public void viewAllAccountsDetails() {

    }

    @Override
    public void viewAllDepositsDetails() {

    }

    @Override
    public void ViewAllActivitiesDetails() {

    }

    @Override
    public void updateSystemProperty() {

    }

    @Override
    public void updateClientDetails() {

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
