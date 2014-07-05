package main.services.impl;

import main.db_access_layer.managers.AccountManager;
import main.db_access_layer.managers.DepositManager;
import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.db_access_layer.managers.impl.DepositManagerImpl;
import main.exceptions.DbConnectorException;
import main.exceptions.MaintenanceException;
import main.model.Account;
import main.model.Deposit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class CloseDepositsThread implements Runnable {

    private static int DAY = 86400000;
    @Override
    public void run() {
        DepositManager dm = null;
        try {
            dm = new DepositManagerImpl();
        } catch (DbConnectorException e) {
            e.printStackTrace();    //TODO - Deal with exception
        }
        HashSet<Deposit> allExpired = null;
        try {
            allExpired = dm.allExpiredDeposits();
        } catch (DbConnectorException e) {
            e.printStackTrace();    //TODO - Deal with exception
        }
        for (Deposit deposit : allExpired) {
            try {
                closeDeposit(deposit);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // TODO - Sleep for a DAY
    }
    
    private void closeDeposit(Deposit deposit) throws DbConnectorException, MaintenanceException {
        AccountManager am = new AccountManagerImpl();
        HashSet<Account> accounts = am.allClientsAccounts(deposit.getClientId());
        Iterator<Account> it = accounts.iterator();
        if (it.hasNext()){
            Account account = it.next();
            account.setBalance(account.getBalance() + deposit.getBalance());
            am.updateAccount(account);
            DepositManager dm = new DepositManagerImpl();
            dm.closeDeposit(deposit);
        } else {
            String msg = "Couldn't find an account for client " + deposit.getClientId();
            
            throw new MaintenanceException(msg);
        }
    }
}
