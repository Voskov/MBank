package test;

import main.managers.impl.AccountManagerImpl;
import main.model.Account;

public class TestAccount {
    private static AccountManagerImpl AC = new AccountManagerImpl();

    public static void main(String[] args) {
//        ctrateAccounttest();
//        deleteAccount();
//        depositToAccount();
//        withdraw();
//        withdrawTooMuch();
//        checkBalanceTest();
        getAccountTest();

        AC.disconnect();
    }

    static void ctrateAccounttest() {
//        AccountDbConnector AC = new AccountDbConnector();
        AC.createAccount(123456, 98765432, 12345.00, 7654, "Another test");
    }

    static void deleteAccount() {
//        AccountDbConnector AC = new AccountDbConnector();
        AC.deleteAccount(123456);
    }

    static void depositToAccount() {
        AC.depositToAccount(123456, 100);
    }

    static void withdraw() {
        AC.withdrawFromAccount(123456, 100);

    }

    static void withdrawTooMuch() {
        AC.withdrawFromAccount(123456, 999999);
    }

    static void checkBalanceTest() {
        AC.checkBalance(123456);
    }

    static void getAccountTest(){
        Account ac = AC.getAccount(123456);
        ac.toString();
    }
}
