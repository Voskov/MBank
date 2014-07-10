package main.services.impl;

import main.AccountType;
import main.DepositType;
import main.db_access_layer.managers.*;
import main.db_access_layer.managers.impl.*;
import main.exceptions.ClientActionException;
import main.exceptions.ClientException;
import main.exceptions.DbConnectorException;
import main.model.Account;
import main.model.Activity;
import main.model.Client;
import main.model.Deposit;
import main.services.ClientAction;

import javax.security.auth.login.AccountException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ClientActionImpl implements ClientAction {
    @Override
    public void withdrawFromAccount(Client client, double withdrawalAmount) throws DbConnectorException, ClientException {
        AccountManager accountManager = new AccountManagerImpl();
        HashSet<Account> allAccounts = accountManager.allClientsAccounts(client.getClientId());
        if (allAccounts.size() > 1) {
            throw new ClientException("More than one account, please choose account");
        } else if (allAccounts.size() < 1) {
            throw new ClientException("No accounts for this client");
        }
        Iterator<Account> accountsIterator = allAccounts.iterator();
        Account account = accountsIterator.next();
        withdrawFromAccount(account, withdrawalAmount);
    }

    @Override
    public void withdrawFromAccount(Account account, double withdrawalAmount) throws DbConnectorException, ClientException {
        AccountManager accountManager = new AccountManagerImpl();
        PropertyManager propertyManager = new PropertyManagerImpl();
        double commission = propertyManager.getProperty("commission_rate");
        double balance = account.getBalance();
        if (withdrawalAmount > balance + commission) {
            throw new ClientException("Not enough money in the account");
        }
        account.setBalance(balance - withdrawalAmount - commission);
        accountManager.updateAccount(account);
    }

    @Override
    public void withdrawFromAccount(long account_id, double withdrawalAmount) throws DbConnectorException, ClientException {
        AccountManager accountManager = new AccountManagerImpl();
        Account dbAccount = accountManager.findAccount(account_id);
        withdrawFromAccount(dbAccount, withdrawalAmount);
    }

    @Override
    public void depositToAccount(Client client, double depositAmount) throws DbConnectorException, AccountException, ClientException {
        AccountManager accountManager = new AccountManagerImpl();
        HashSet<Account> allAccounts = accountManager.allClientsAccounts(client.getClientId());

        //Not REALLY necessary
        if (allAccounts.size() > 1) {
            throw new AccountException("More than one account, please choose account");
        } else if (allAccounts.size() < 1) {
            throw new ClientException("No accounts for this client");
        }
        Account account = allAccounts.iterator().next();
        depositToAccount(account, depositAmount);
    }

    @Override
    public void depositToAccount(Account account, double depositAmount) throws DbConnectorException, AccountException {
        ClientManager cm = new ClientManagerImpl();
        Client dbClient = cm.findClient(account.getClientId());
        PropertyManager pm = new PropertyManagerImpl();
        double commission = pm.getProperty("commission_rate");
        AccountManager accountManager = new AccountManagerImpl();
        Account dbAccount = accountManager.findAccount(account.getAccountId());
        double newBalance = dbAccount.getBalance() + depositAmount - commission;
        if (newBalance < (-1 * dbAccount.getCreditLimit())) {
            // in case the client tries to deposit less than the commission
            throw new AccountException("Not enough money in the account to perform this action.");
        }
        dbAccount.setBalance(newBalance);
        accountManager.updateAccount(dbAccount);
        updateClientStatus(dbClient);

        //Add activity
        String msg = "Client " + dbClient.getClientName() + " has deposited " + depositAmount;
        Activity activity = new Activity(dbClient.getClientId(), depositAmount, new Date(), commission, msg);
        ActivityManager actMan = new ActivityManagerImpl();
        actMan.addActivity(activity);

    }

    @Override
    public void depositToAccount(long accountId, double depositAmount) throws AccountException, DbConnectorException {
        AccountManager am = new AccountManagerImpl();
        Account dbAccount = am.findAccount(accountId);
        depositToAccount(dbAccount, depositAmount);
    }

    public void updateClientStatus(Client client) throws DbConnectorException {
        AccountManager am = new AccountManagerImpl();
        HashSet<Account> allAccounts = am.allClientsAccounts(client.getClientId());
        double sum = 0;
        for (Account acc : allAccounts) {
            sum += acc.getBalance();
        }
        PropertyManager pm = new PropertyManagerImpl();
        HashMap<AccountType, Double> rates = new HashMap<AccountType, Double>();
        rates.put(AccountType.REGULAR, pm.getProperty(AccountType.REGULAR, "deposit_rate"));
        rates.put(AccountType.GOLD, pm.getProperty(AccountType.GOLD, "deposit_rate"));
        rates.put(AccountType.PLATINUM, pm.getProperty(AccountType.PLATINUM, "deposit_rate"));
        if (sum > rates.get(AccountType.PLATINUM)) {
            client.setAccountType(AccountType.PLATINUM);
        } else if (sum < rates.get(AccountType.PLATINUM) && sum > rates.get(AccountType.GOLD)) {
            client.setAccountType(AccountType.GOLD);
        } else if (sum < rates.get(AccountType.GOLD)) {
            client.setAccountType(AccountType.REGULAR);
        }
        ClientManager cm = new ClientManagerImpl();
        cm.updateClient(client);
    }

    public void updateClientStatus(long clientId) throws DbConnectorException {
        ClientManager cm = new ClientManagerImpl();
        Client dbClient = cm.findClient(clientId);
        updateClientStatus(dbClient);
    }

    public void determineDepositType() {

    }

    @Override
    public void createNewDeposit(Client client, Date openingDate, double amount) throws DbConnectorException, ClientActionException {
        AccountManager am = new AccountManagerImpl();
        ClientManager cm = new ClientManagerImpl();
        HashSet<Account> allAccounts = am.allClientsAccounts(client.getClientId());
        Account dbAccount = null;
        Date today = new Date();
        try {
            dbAccount = allAccounts.iterator().next();
        } catch (Exception e) {
            String msg = "Client has more that one account (CA-7263)";
            throw new ClientActionException(msg, e);
        }
        if (dbAccount.getBalance() < amount) {
            String msg = "There is not enough money in the account to deposit";
            throw new ClientActionException(msg);
        }
        Deposit newDeposit = new Deposit(client.getClientId(), amount, DepositType.LONG, openingDate, today);
//TODO - Implement


        dbAccount.setBalance(dbAccount.getBalance() - amount);
    }

    @Override
    public void createNewDeposit(Deposit deposit) throws DbConnectorException {
        ClientManager cm = new ClientManagerImpl();
        Client client = cm.findClient(deposit.getClientId());
        createNewDeposit(deposit, client);
    }

    @Override
    public void createNewDeposit(Deposit deposit, Client client) throws DbConnectorException {
        PropertyManager pm = new PropertyManagerImpl();

        Date closingDate = deposit.getClosingDate();
        //TODO - verify date
        int closingDateInDays = 0;


//        if (closingDate > 40 years){
//        String msg = "Can't open a deposit for that long";
//        throw Exception
//        } else if (deposit.getType() == DepositType.SHORT && closingDate > 1 year){
//        String msg = "Can't open a SHORT deposit for that long";
//        throw Exception
//    }

        double dayliInterest = pm.getProperty(client.getAccountType(), "daily_interest");
        //TODO - finish this
        double estimatedInterest = deposit.getBalance() * (dayliInterest + 1) * closingDateInDays;
        deposit.setEstimatedBalance((int) (deposit.getBalance() + estimatedInterest));
    }

    private double calculateInterest(double initialAmount, double dailyInterest, int amountOfDays) {
        return initialAmount * (1 + dailyInterest) * amountOfDays;
    }

    @Override
    public void preOpenDeposit(long deposit_id) throws DbConnectorException, ClientException {
        PropertyManager pm = new PropertyManagerImpl();
        DepositManager dm = new DepositManagerImpl();
        AccountManager am = new AccountManagerImpl();
        ClientManager cm = new ClientManagerImpl();

        Deposit dbDeposit = dm.findDeposit(deposit_id);
        if (dbDeposit.getType() != DepositType.LONG) {
            throw new ClientException("Only long deposits may be pre-opened");
        }
        Client client = cm.findClient(dbDeposit.getClientId());
        HashSet<Account> allAccounts = am.allClientsAccounts(client.getClientId());
        if (allAccounts.isEmpty()) {
            throw new ClientException("Client doesn't have accounts");
        }
        Account account = allAccounts.iterator().next();
        double preOpenFee = pm.getProperty("pre_open_fee");
        account.setBalance(account.getBalance() + (dbDeposit.getBalance() * (100 - preOpenFee)));
        am.updateAccount(account);
        dm.closeDeposit(deposit_id);
    }

    public void preopenDeposit(Deposit deposit) throws ClientException, DbConnectorException {
        preOpenDeposit(deposit.getDepositId());
    }

    @Override
    public void updateClientDetails(Client updatedClient) throws DbConnectorException {
        ClientManager cm = new ClientManagerImpl();
        Client dbClient = cm.findClient(updatedClient);
        if (updatedClient.getPhone() != null) dbClient.setPhone(updatedClient.getPhone());
        if (updatedClient.getEmail() != null) dbClient.setEmail(updatedClient.getEmail());
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
        dbClient.setPassword(null);
        dbClient.setClientId(Long.parseLong(null));
        return dbClient;
    }

    @Override
    public Account viewAccountDetails(Account account) throws DbConnectorException {
        return viewAccountDetails(account.getAccountId());
    }

    @Override
    public Account viewAccountDetails(long AccountId) throws DbConnectorException {
        AccountManager am = new AccountManagerImpl();
        Account dbAccount = am.findAccount(AccountId);
        return dbAccount;
    }

    public HashSet<Deposit> viewClientDeposits(Client client) throws DbConnectorException {
        return viewClientDeposits(client.getClientId());
    }

    @Override
    public HashSet<Deposit> viewClientDeposits(long clientId) throws DbConnectorException {
        DepositManager dm = new DepositManagerImpl();
        HashSet<Deposit> allClientsDeposits = dm.allClientsDeposits(clientId);
        for (Deposit deposit : allClientsDeposits) {
            deposit.setClientId(0);
            deposit.setDepositId(0);
        }
        return allClientsDeposits;
    }

    @Override
    public HashSet<Activity> viewClientActivities(Client client) throws SQLException, DbConnectorException {
        ActivityManager am = new ActivityManagerImpl();
        HashSet<Activity> allClientActivities = am.findAllClientActivities(client.getClientId());
        for (Activity activity : allClientActivities) {
            activity.setActivityId(Long.parseLong(null));
            activity.setClientId(Long.parseLong(null));
        }
        return allClientActivities;
    }
}
