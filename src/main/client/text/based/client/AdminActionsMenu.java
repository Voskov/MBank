package main.client.text.based.client;

import main.db_access_layer.managers.AccountManager;
import main.db_access_layer.managers.ClientManager;
import main.db_access_layer.managers.PropertyManager;
import main.db_access_layer.managers.impl.AccountManagerImpl;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.db_access_layer.managers.impl.PropertyManagerImpl;
import main.exceptions.ClientException;
import main.exceptions.DbConnectorException;
import main.model.Account;
import main.model.Client;
import main.services.AdminAction;
import main.services.impl.AdminActionImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

import static main.client.text.based.client.Input.*;

public class AdminActionsMenu {
    private static Logger logger = Logger.getLogger("AdminActionsMenu");
    private static String[] INPUT_OPTIONS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

    public static void main(String[] args) throws DbConnectorException, ClientException, InterruptedException {
        adminActionsClient();

    }

    public static void adminActionsClient() throws DbConnectorException, ClientException, InterruptedException {
        boolean performAnAction = true;
        while (performAnAction) {
            System.out.println("Admin Actions");
            System.out.println("-------------");
            System.out.println("Please choose one of the following:");
            System.out.println("1 - Add a new client");
            System.out.println("2 - Update client details");
            System.out.println("3 - Remove a client");
            System.out.println("4 - Create a new account");
            System.out.println("5 - Remove account");
            System.out.println("6 - View client details");
            System.out.println("7 - View all client details");
            System.out.println("8 - View account details");
            System.out.println("9 - View all accounts details");
            System.out.println("10 - View a deposit");
            System.out.println("11 - View all deposits");
            System.out.println("12 - View client activities");
            System.out.println("13 - View all activities");
            System.out.println("14 - View a system property");
            System.out.println("15 - Update a system property");
            System.out.println("Please enter your selection");

            String input = multipleChoiceInput(INPUT_OPTIONS);
            switch (Integer.parseInt(input)) {
                case 1:
                    addNewClient();
                    break;
                case 2:
                    updateClientDetails();
                    break;
                case 3:
                    removeClient();
                    break;
                case 4:
                    createAccount();
                    break;
                case 5:
                    removeAccount();
                    break;
                case 6:
                    viewClientDetails();
                    break;
                case 7:
                    viewAllClientDetails();
                    break;
                case 8:
                    viewAccountDetails();
                    break;
                case 9:
                    viewAllAccountDetails();
                    break;
                case 10:
                    viewDeposit();
                    break;
                case 11:
                    viewAllDeposits();
                    break;
                case 12:
                    viewClientActivities();
                    break;
                case 13:
                    viewAllActivities();
                    break;
                case 14:
                    viewSystemProperty();
                    break;
                case 15:
                    updateSystemProperty();
                    break;
                default:
                    System.out.println("There was a problem");
                    break;
            }
            performAnAction = anotherAction();
        }
    }

    private static void addNewClient() throws DbConnectorException {
        System.out.println("Add a new client");
        System.out.println("----------------");
        System.out.println("Please, prepare a username, a password, an address, en email and a phone number");
        System.out.println("Please enter a user name");
        String username = Input.stringInput();
        System.out.println("Please enter a password");
        String password = Input.stringInput();
        System.out.println("Please enter a address");
        String address = Input.stringInput();
        System.out.println("Please enter a email");
        String email = Input.stringInput();
        System.out.println("Please enter a phone");
        String phone = Input.stringInput();
        System.out.println("Please enter a comment");
        String comment = Input.stringInput();
        System.out.println("How much money is the new client would like to deposit?");
        Double amount = Input.doubleInput();

        Client newClient = new Client(username, password, address, email, phone, comment);
        AdminAction aa = new AdminActionImpl();
        aa.addNewClient(newClient, amount);
        logger.log(Level.INFO, "The new client has been created");
    }

    private static void updateClientDetails() throws DbConnectorException {
        System.out.println("Update client detail");
        System.out.println("--------------------");
        System.out.println("Please enter the username of the client you would like to update");
        String username = Input.stringInput();
        ClientManager cm = new ClientManagerImpl();
        Client dbClient = cm.findClient(username);
        if (dbClient == null) {
            System.out.println("Could not find a user named " + username);
            return;
        }
        System.out.println("What would you like to update?");
        System.out.println("------------------------------");
        System.out.println("1 - Username");
        System.out.println("2 - Password");
        System.out.println("3 - Address");
        System.out.println("4 - Email");
        System.out.println("5 - Phone");
        System.out.println("6 - Comment");
        System.out.println("Please enter your selection");
        String input = Input.multipleChoiceInput(new String[]{"1", "2", "3", "4", "5", "6"});
        String updateValue;
        boolean updateMore = true;
        while (updateMore) {
            switch (Integer.parseInt(input)) {
                case 1:
                    System.out.println("Please enter the desired username");
                    updateValue = Input.stringInput();
                    dbClient.setClientName(updateValue);
                    break;
                case 2:
                    System.out.println("Please enter the desired password");
                    updateValue = Input.stringInput();
                    dbClient.setPassword(updateValue);
                    break;
                case 3:
                    System.out.println("Please enter the desired address");
                    updateValue = Input.stringInput();
                    dbClient.setAddress(updateValue);
                    break;
                case 4:
                    System.out.println("Please enter the desired email");
                    updateValue = Input.stringInput();
                    dbClient.setEmail(updateValue);
                    break;
                case 5:
                    System.out.println("Please enter the desired phone");
                    updateValue = Input.stringInput();
                    dbClient.setPhone(updateValue);
                    break;
                case 6:
                    System.out.println("Please enter the desired comment");
                    updateValue = Input.stringInput();
                    dbClient.setComment(updateValue);
                    break;
            }
            System.out.println("Would you like to update anything else?");
            String more = Input.stringInput();
            updateMore = "y".equals(more);
        }
        AdminAction aa = new AdminActionImpl();
        aa.updateClientDetails(dbClient);
        logger.log(Level.INFO, "The client has been updated");
    }

    public static void removeClient() throws DbConnectorException, ClientException {
        System.out.println("Update client detail");
        System.out.println("--------------------");
        System.out.println("Please enter the username of the client you would like to remove");
        String username = Input.stringInput();
        ClientManager cm = new ClientManagerImpl();
        Client dbClient = cm.findClient(username);
        System.out.println("Are you absolutely sure that you would like to remove " + dbClient.getClientName() + "?");
        String sure = Input.stringInput();
        if ("y".equals(sure)) {
            AdminAction aa = new AdminActionImpl();
            aa.removeClient(dbClient);
        }
        logger.log(Level.INFO, "The client has been removed");
    }

    public static void createAccount() throws DbConnectorException, InterruptedException {
        System.out.println("Create an account");
        System.out.println("------------------");
        System.out.println("Please enter the client ID or username");
        String stringInput = Input.stringInput();
        long longInput = 0;
        Client dbClient;
        try {
            longInput = Long.parseLong(stringInput);
        } catch (Exception e) {
            // Do nothing
        }
        ClientManager cm = new ClientManagerImpl();
        try {
            if (longInput > 0) {
                dbClient = cm.findClient(longInput);
            } else {
                dbClient = cm.findClient(stringInput);
            }
        } catch (DbConnectorException e) {
            System.out.println("Could not find a user by the parameters");
            return;
        }
        double newAmount = doubleInput();
        PropertyManager pm = new PropertyManagerImpl();
        long creditLimit = (long) pm.getProperty(dbClient.getAccountType(), "deposit_credit");

        Account newAccount = new Account(dbClient.getClientId(), newAmount, creditLimit, "New account");
        AccountManager am = new AccountManagerImpl();
        am.createAccount(newAccount);
        Thread.sleep(400);
        logger.log(Level.INFO, "The account has been created");
    }

    public static void removeAccount() throws DbConnectorException, InterruptedException {
        System.out.println("remove an account");
        System.out.println("------------------");
        System.out.println("Please enter an account number");
        long accountNumber = Input.longInput();
        AccountManager am = new AccountManagerImpl();
        Account dbAccount = null;
        try {
            dbAccount = am.findAccount(accountNumber);
        } catch (DbConnectorException e) {
            System.out.println("Could not find the account");
        }
        AdminAction aa = new AdminActionImpl();
        aa.removeAccount(dbAccount);
        Thread.sleep(400);

    }

    public static void viewClientDetails() {
        System.out.println("View clients details");
        System.out.println("------------------");

    }

    public static void viewAllClientDetails() {
        System.out.println("View all clients details");
        System.out.println("------------------");

    }

    public static void viewAccountDetails() {
        System.out.println("View accounts details");
        System.out.println("------------------");

    }

    public static void viewAllAccountDetails() {
        System.out.println("View all accounts details");
        System.out.println("------------------");

    }

    public static void viewDeposit() {
        System.out.println("View a deposit");
        System.out.println("------------------");

    }

    public static void viewAllDeposits() {
        System.out.println("View all deposits");
        System.out.println("------------------");

    }

    public static void viewClientActivities() {
        System.out.println("View clients activities");
        System.out.println("------------------");

    }

    public static void viewAllActivities() {
        System.out.println("View all activities");
        System.out.println("------------------");

    }

    public static void viewSystemProperty() {
        System.out.println("View system property");
        System.out.println("------------------");

    }

    public static void updateSystemProperty() {
        System.out.println("Update system property");
        System.out.println("------------------");

    }
}
