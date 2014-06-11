package main.services;

import main.DepositType;
import main.model.Account;
import main.model.Client;

import java.util.Date;

public interface ClientAction extends Action{

    void withdrawFromAccount(Account account, double withdrawalAmount) throws Exception;
    void withdrawFromAccount(long accountId, double withdrawalAmount) throws Exception;
    void withdrawFromAccount(Client client, double withdrawalAmount) throws Exception; // Assuming that there's only one account for a client, for now

    void depositToAccount(Client client, double depositAmount) throws Exception;
    void depositToAccount(Account account, double depositAmount);
    void depositToAccount(long accountId, double depositAmount);

    void createNewDeposit(Client client, double amount, DepositType type, Date openDate);

    void createNewDeposit(Account accountt, double amount, DepositType type, Date openDate);

    void preOpenDeposit(long deposit_id) throws Exception;
}
