package main.services.impl;

import main.db_access_layer.managers.AccountManager;
import main.db_access_layer.managers.ClientManager;
import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.model.Account;
import main.model.Client;
import main.services.AdminAction;

public class AdminActionImpl implements AdminAction {
    @Override
    public Client addNewClient() {
        return null;
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
