package main.services;

import main.model.Account;
import main.model.Client;

/**
 * Created by Einstine on 04/06/2014.
 */
public interface ClientAction extends Action{

    void withdrawFromAccount(Account account, double withdrawalAmount) throws Exception;

    void withdrawFromAccount(long account_id, double withdrawalAmount) throws Exception;

    void withdrawFromAccount(Client client, double withdrawalAmount) throws Exception; // Assuming that there's only one account for a client, for now

    void depositToAccount();

    void createNewDeposit();

    void preopenDeposit();
}
