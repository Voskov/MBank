package main.services;

/**
 * Created by Einstine on 04/06/2014.
 */
public interface ClientAction extends Action{

    void withdrawFromAccount();

    void depositToAccount();

    void createNewDeposit();

    void preopenDeposit();
}
