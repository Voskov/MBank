package main.services.impl;

import main.DepositType;
import main.db_access_layer.managers.AccountManager;
import main.db_access_layer.managers.ClientManager;
import main.db_access_layer.managers.DepositManager;
import main.db_access_layer.managers.PropertyManager;
import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.db_access_layer.managers.impl.DepositManagerImpl;
import main.db_access_layer.managers.impl.PropertyManagerImpl;
import main.model.Account;
import main.model.Client;
import main.model.Deposit;
import main.services.ClientAction;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

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
        double commission = 0.5;
//        double commission = propertyManager.getProperty("commission_rate");
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
    public void createNewDeposit(Client client, double amount, DepositType type, Date openDate) {
        AccountManager am = new AccountManagerImpl();
        HashSet<Account> allAccounts = am.allClientsAccounts(client.getClient_id());
        if (allAccounts.iterator().hasNext()) {
            Account account = allAccounts.iterator().next();
            createNewDeposit(account, amount, type, openDate);
        }
    }

    @Override
    public void createNewDeposit(Account account, double amount, DepositType type, Date openDate) {

//        PropertyManager
//        if (account.getBalance() < amount) {
//
//        }
    }


    @Override
    public void preOpenDeposit(long deposit_id) throws Exception {
        PropertyManager pm = new PropertyManagerImpl();
        DepositManager dm = new DepositManagerImpl();
        AccountManager am = new AccountManagerImpl();
        ClientManager cm = new ClientManagerImpl();

        Deposit dbDeposit = dm.findDeposit(deposit_id);
        if (dbDeposit.getType() != DepositType.LONG){
            throw new Exception("Only long deposits may be pre-opened");
        }
        Client client = cm.findClient(dbDeposit.getClientId());
        HashSet<Account> allAccounts = am.allClientsAccounts(client.getClient_id());
        if (allAccounts.isEmpty()){
            throw new Exception("Client doesn't have accounts");
        }
        Iterator<Account> it = allAccounts.iterator();
        Account account = it.next();
        double preOpenFee = pm.getProperty("pre_open_fee");
        account.setBalance(account.getBalance() + (dbDeposit.getBalance() * (100 - preOpenFee)));
        am.updateAccount(account);
        dm.closeDeposit(deposit_id);
    }

    public void preopenDeposit(Deposit deposit) throws Exception {
        preOpenDeposit(deposit.getDepositId());
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
