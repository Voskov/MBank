package main.services.impl;

import main.AccountType;
import main.db_access_layer.managers.ClientManager;
import main.db_access_layer.managers.DepositManager;
import main.db_access_layer.managers.PropertyManager;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.db_access_layer.managers.impl.DepositManagerImpl;
import main.db_access_layer.managers.impl.PropertyManagerImpl;
import main.model.Client;
import main.model.Deposit;

import java.util.HashSet;

public class UpdateInterests implements Runnable {
    private static double regular_daily_interest;
    private static double gold_daily_interest;
    private static double platinum_daily_interest;

    @Override
    public void run() {
        DepositManager dm = new DepositManagerImpl();

        updateRates();

        HashSet<Deposit> allDeposits = dm.allDeposits();
        for (Deposit deposit : allDeposits) {
            try {
                updateInterest(deposit);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateInterest(Deposit deposit) throws Exception {
        DepositManager dm = new DepositManagerImpl();
        ClientManager cm = new ClientManagerImpl();
        AccountType accountType = cm.findClient(deposit.getClientId()).getAccountType();
        double interest = 1;

        // TODO - do a switch

        if (accountType == AccountType.REGULAR) {
            interest = regular_daily_interest;
        } else if (accountType == AccountType.GOLD) {
            interest = gold_daily_interest;
        } else if (accountType == AccountType.PLATINUM) {
            interest = platinum_daily_interest;
        } else {
            String msg = "Account doesn't have a type";
            throw new Exception(msg);
        }
        deposit.setBalance(deposit.getBalance() * interest);
        dm.updateDeposit(deposit);
    }

    private void updateRates() {
        PropertyManager pm = new PropertyManagerImpl();
        String[] types = {"regular_daily_interest", "gold_daily_interest", "platinum_daily_interest"};
        try {
            regular_daily_interest = pm.getProperty("regular_daily_interest");
            gold_daily_interest = pm.getProperty("gold_daily_interest");
            platinum_daily_interest = pm.getProperty("platinum_daily_interest");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
