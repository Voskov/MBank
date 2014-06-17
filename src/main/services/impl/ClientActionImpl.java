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
        AccountManager accountManager = new AccountManagerImpl();
        PropertyManager propertyManager = new PropertyManagerImpl();
        double commission = propertyManager.getProperty("commission_rate");
        double balance = account.getBalance();
        if (withdrawalAmount > balance + commission) {
            throw new Exception("Not enough money in the account");
        }
        account.setBalance(balance - withdrawalAmount - commission);
        accountManager.updateAccount(account);
    }

    @Override
    public void withdrawFromAccount(long account_id, double withdrawalAmount) throws Exception {
        AccountManager accountManager = new AccountManagerImpl();
        Account dbAccount = accountManager.findAccount(account_id);
        withdrawFromAccount(dbAccount, withdrawalAmount);
    }

    @Override
    public void depositToAccount(Client client, double depositAmount) throws Exception {
        AccountManager accountManager = new AccountManagerImpl();
        HashSet<Account> allAccounts = accountManager.allClientsAccounts(client.getClient_id());

        //Not REALLY necessary
        if (allAccounts.size() > 1) {
            throw new Exception("More than one account, please choose account");
        } else if (allAccounts.size() < 1) {
            throw new Exception("No accounts for this client");
        }
        Account account = allAccounts.iterator().next();
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
    public void createNewDeposit(Deposit deposit) throws Exception {
        ClientManager cm = new ClientManagerImpl();
        Client client = cm.findClient(deposit.getClientId());
        createNewDeposit(deposit, client);
    }

    @Override
    public void createNewDeposit(Deposit deposit, Client client) throws Exception {
        PropertyManager pm = new PropertyManagerImpl();

        Date closingDate = deposit.getClosingDate();
        //TODO - verify date
//        if (closingDate > 40 years){
//        String msg = "Can't open a deposit for that long";
//        throw Exception
//        } else if (deposit.getType() == DepositType.SHORT && closingDate > 1 year){
//        String msg = "Can't open a SHORT deposit for that long";
//        throw Exception
//    }

        double interest = pm.getProperty(client.getAccountType(), "daily_interest");

    }

    @Override
    public void preOpenDeposit(long deposit_id) throws Exception {
        PropertyManager pm = new PropertyManagerImpl();
        DepositManager dm = new DepositManagerImpl();
        AccountManager am = new AccountManagerImpl();
        ClientManager cm = new ClientManagerImpl();

        Deposit dbDeposit = dm.findDeposit(deposit_id);
        if (dbDeposit.getType() != DepositType.LONG) {
            throw new Exception("Only long deposits may be pre-opened");
        }
        Client client = cm.findClient(dbDeposit.getClientId());
        HashSet<Account> allAccounts = am.allClientsAccounts(client.getClient_id());
        if (allAccounts.isEmpty()) {
            throw new Exception("Client doesn't have accounts");
        }
        Account account = allAccounts.iterator().next();
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
