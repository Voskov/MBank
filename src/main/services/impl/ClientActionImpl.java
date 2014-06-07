package main.services.impl;

import main.db_access_layer.managers.AccountManager;
import main.db_access_layer.managers.ClientManager;
import main.db_access_layer.managers.PropertyManager;
import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.db_access_layer.managers.impl.PropertyManagerImpl;
import main.model.Account;
import main.model.Client;
import main.services.ClientAction;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Einstine on 04/06/2014.
 */
public class ClientActionImpl implements ClientAction {
    @Override
    public void withdrawFromAccount(Client client, double withdrawalAmount) throws Exception {
        AccountManager accountManager = new AccountManagerImpl();
        HashSet<Account> allAccounts = accountManager.allClientsAccounts(client.getClient_id());
        if (allAccounts.size() > 1) {
            throw new Exception("More than one account, please choose account");
        } else if (allAccounts.size() < 1) {
            throw new Exception("No accounts for this client");
        }
        Iterator<Account> accountsIterator = allAccounts.iterator();
        Account account = accountsIterator.next();
        withdrawFromAccount(account, withdrawalAmount);
    }

    @Override
    public void withdrawFromAccount(Account account, double withdrawalAmount) throws Exception {
        withdrawFromAccount(account.getAccount_id(), withdrawalAmount);
    }

    @Override
    public void withdrawFromAccount(long account_id, double withdrawalAmount) throws Exception {
        AccountManager accountManager = new AccountManagerImpl();
        Account dbAccount = accountManager.findAccount(account_id);
        PropertyManager propertyManager = new PropertyManagerImpl();
        double commission = propertyManager.getProperty("commission_rate");
        double balance = dbAccount.getBalance();
        if (withdrawalAmount > balance + commission) {
            throw new Exception("Not enough money in the account");
        }
        dbAccount.setBalance(balance - withdrawalAmount - commission);
        accountManager.updateAccount(dbAccount);
    }

    @Override
    public void depositToAccount(Client client, double depositAmount) throws Exception {
        AccountManager accountManager = new AccountManagerImpl();
        HashSet<Account> allAccounts = accountManager.allClientsAccounts(client.getClient_id());
        if (allAccounts.size() > 1) {
            throw new Exception("More than one account, please choose account");
        } else if (allAccounts.size() < 1) {
            throw new Exception("No accounts for this client");
        }
        Iterator<Account> accountsIterator = allAccounts.iterator();
        Account account = accountsIterator.next();
        depositToAccount(account, depositAmount);
    }

    @Override
    public void depositToAccount(Account account, double depositAmount) {
        depositToAccount(account.getAccount_id(), depositAmount);
    }

    @Override
    public void depositToAccount(long accountId, double depositAmount) {
        //TODO - add activity
        //TODO - update client status
        AccountManager accountManager = new AccountManagerImpl();
        Account dbAccount = accountManager.findAccount(accountId);
        double newBalance = dbAccount.getBalance() + depositAmount;
        dbAccount.setBalance(newBalance);
        accountManager.updateAccount(dbAccount);
    }

    @Override
    public void createNewDeposit() {

    }

    @Override
    public void preopenDeposit() {

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
