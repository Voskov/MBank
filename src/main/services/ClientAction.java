package main.services;

import main.DepositType;
import main.model.Account;
import main.model.Client;
import main.model.Deposit;

import java.util.Date;

public interface ClientAction extends Action{

    void withdrawFromAccount(Account account, double withdrawalAmount) throws Exception;
    void withdrawFromAccount(long accountId, double withdrawalAmount) throws Exception;
    void withdrawFromAccount(Client client, double withdrawalAmount) throws Exception; // Assuming that there's only one account for a client, for now

    void depositToAccount(Client client, double depositAmount) throws Exception;
    void depositToAccount(Account account, double depositAmount);
    void depositToAccount(long accountId, double depositAmount);

    void createNewDeposit(Deposit deposit) throws Exception;
    void createNewDeposit(Deposit deposit, Client client) throws Exception;

//    void createNewDeposit(Client client, double amount, DepositType type, Date closing_date) throws Exception;
//    void createNewDeposit(Client client, double amount, DepositType type, Date openDate) throws Exception;

//    void createNewDeposit(Deposit deposit, Client client) throws Exception;

    void preOpenDeposit(long deposit_id) throws Exception;
}
